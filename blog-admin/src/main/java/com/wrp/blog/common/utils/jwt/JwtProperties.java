package com.wrp.blog.common.utils.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wrp
 * @since 2024-11-19 23:05
 **/
@Data
@Component
@ConfigurationProperties(prefix = "auth.jwt")
public class JwtProperties {
    private String secretKey;
    /**
     * 毫秒单位
     */
    private Long ttl;
}
