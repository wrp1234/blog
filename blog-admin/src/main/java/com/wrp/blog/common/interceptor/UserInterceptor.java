package com.wrp.blog.common.interceptor;

import com.wrp.blog.common.dict.ResultCode;
import com.wrp.blog.common.exception.SystemException;
import com.wrp.blog.common.utils.jwt.JwtUtils;
import com.wrp.blog.user.UserInfoContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author wrp
 * @since 2024-11-19 23:22
 **/
@Component
@AllArgsConstructor
public class UserInterceptor implements HandlerInterceptor {

    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserInfoContext.set(jwtUtils.parseJWT(getToken(request)));
        return true;
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(!StringUtils.hasText(token)) {
            token = request.getParameter(HttpHeaders.AUTHORIZATION);
        }
        if(!StringUtils.hasText(token)) {
            throw SystemException.of(ResultCode.MISS_TOKEN);
        }
        if(!token.startsWith(JwtUtils.TOKEN_PREFIX)) {
            throw SystemException.of(ResultCode.INVALID_TOKEN);
        }
        return token.replace(JwtUtils.TOKEN_PREFIX, "");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserInfoContext.remove();
    }
}
