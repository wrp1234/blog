package com.wrp.blog.api;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @author wrp
 * @since 2024-11-21 22:35
 **/
public interface CaptchaService {

    void generateCaptcha(String key, HttpServletResponse response);

    void verify(String key, String code);
}
