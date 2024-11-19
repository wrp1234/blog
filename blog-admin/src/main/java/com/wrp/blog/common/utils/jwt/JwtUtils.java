package com.wrp.blog.common.utils.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrp.blog.common.dict.ResultCode;
import com.wrp.blog.common.exception.SystemException;
import com.wrp.blog.user.vo.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wrp
 * @since 2024-11-19 23:04
 **/
@Component
public class JwtUtils {
    private final JwtProperties jwtProperties;
    private final ObjectMapper objectMapper;
    public static final String TOKEN_PREFIX = "Bearer ";
    private final SecretKey key;

    public JwtUtils(JwtProperties jwtProperties, ObjectMapper objectMapper) {
        this.jwtProperties = jwtProperties;
        this.objectMapper = objectMapper;
        key = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
    }

    /**
     * 创建JWT
     * @param userInfo 用户信息
     * @return token
     */
    public String createJWT(UserInfo userInfo) {
        Map<String, Object> claims = new HashMap<>();
        try {
            claims.put(Claims.SUBJECT, objectMapper.writeValueAsString(userInfo));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return createJWT(jwtProperties.getTtl(), claims);
    }

    /**
     * 生成jwt
     * 使用Hs256算法，私钥使用固定密钥
     * @param ttlMillis  jwt过期时间，单位毫秒
     * @param claims     设置的信息
     * @return token
     */
    private String createJWT(long ttlMillis, Map<String, Object> claims){
        //指定加密算法
        SecureDigestAlgorithm<SecretKey, SecretKey> algorithm = Jwts.SIG.HS256;
        //生成JWT的时间
        long expMillis = System.currentTimeMillis()+ttlMillis;
        Date exp = new Date(expMillis);
        //密钥实例
        return TOKEN_PREFIX + Jwts.builder()
                .signWith(key, algorithm) //设置签名使用的签名算法和签名使用的秘钥
                //如果有私有声明，一点要先设置这个自己创建的私有的声明，这个是给builder的claims赋值，一旦卸载标准的声明赋值之后，就是覆盖了那些标准的声明的
                .expiration(exp)//设置过期时间
                .claims(claims) //设置自定义负载信息
                .compact();
    }

    /**
     * 解析jwt
     * @param token 令牌信息
     * @return username
     */
    public UserInfo parseJWT(String token) {
        try {
            return objectMapper.readValue(Jwts.parser()
                    .verifyWith(key)  //设置签名的密钥
                    .build()
                    .parseSignedClaims(token)//设置要解析的jwt
                    .getPayload()
                    .getSubject(), UserInfo.class);
        } catch (SignatureException | MalformedJwtException e) {
            throw SystemException.of(ResultCode.INVALID_TOKEN);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
