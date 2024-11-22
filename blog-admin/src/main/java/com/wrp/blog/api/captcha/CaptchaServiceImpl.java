package com.wrp.blog.api.captcha;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.wrp.blog.common.dict.ResultCode;
import com.wrp.blog.common.exception.SystemException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author wrp
 * @since 2024-11-21 22:36
 **/
@Service
@AllArgsConstructor
public class CaptchaServiceImpl implements CaptchaService {

    private RedisTemplate<String, Object> redisTemplate;
    private CaptchaProperties captchaProperties;


    @Override
    public void generateCaptcha(String key, HttpServletResponse response) {
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(
                200, 100, 4, 30);
        String code = captcha.getCode();
        redisTemplate.opsForValue().set(captchaProperties.getKeyPrefix() + key, code,
                captchaProperties.getTtl(), TimeUnit.SECONDS);
        try {
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            captcha.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void verify(String key, String code) {
        Object c = redisTemplate.opsForValue().get(captchaProperties.getKeyPrefix() + key);
        if (c == null || !c.equals(code)) {
            throw SystemException.of(ResultCode.INVALID_CAPTCHA);
        }
    }
}
