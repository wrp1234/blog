package com.wrp.blog.api.captcha;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.wrp.blog.common.dict.ResultCode;
import com.wrp.blog.common.exception.SystemException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wrp
 * @since 2024-11-21 22:36
 **/
@Service
public class CaptchaServiceImpl implements CaptchaService {

    private final CaptchaProperties captchaProperties;
    // TODO
    private final Map<String, String> cache = new HashMap<>();

    public CaptchaServiceImpl(CaptchaProperties captchaProperties) {
        this.captchaProperties = captchaProperties;
    }

    @Override
    public void generateCaptcha(String key, HttpServletResponse response) {
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(
                200, 100, 4, 30);
        String code = captcha.getCode().toLowerCase();
        synchronized (cache) {
            if(cache.keySet().size() > 128) {
                cache.clear();
            }
            cache.put(captchaProperties.getKeyPrefix() + key, code);
        }

        try {
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            captcha.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void verify(String key, String code) {
        String k = captchaProperties.getKeyPrefix() + key;
        synchronized (cache) {
            Object c = cache.get(k);
            if (c == null || !c.equals(code.toLowerCase())) {
                throw SystemException.of(ResultCode.INVALID_CAPTCHA);
            }
            cache.remove(k);
        }
    }
}
