package com.wrp.blog.api.captcha;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wrp
 * @since 2024-11-21 22:33
 **/
@RestController
@RequestMapping("captcha")
@AllArgsConstructor
public class CaptchaController {

    private final CaptchaService captchaService;

    @GetMapping
    public void getCaptcha(@RequestParam("k") String key, HttpServletResponse response) {
        captchaService.generateCaptcha(key, response);
    }
}
