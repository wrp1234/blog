package com.wrp.blog.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wrp
 * @since 2024-11-19 23:41
 **/
@Data
@Component
@ConfigurationProperties("url")
public class UrlProperties {
    private List<String> excludes;
}
