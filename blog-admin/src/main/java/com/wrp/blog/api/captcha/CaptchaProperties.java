package com.wrp.blog.api.captcha;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wrp
 * @since 2024-11-21 23:00
 **/
@Data
@Component
@ConfigurationProperties(prefix = "redis.captcha")
public class CaptchaProperties {
    private String keyPrefix;
    private long ttl;
}
