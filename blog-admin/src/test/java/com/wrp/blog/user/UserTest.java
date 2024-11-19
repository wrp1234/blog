package com.wrp.blog.user;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wrp
 * @since 2024-11-19 22:31
 **/
@SpringBootTest
public class UserTest {

    @Resource
    private UserService userService;

    @Test
    public void testRegister() {
//        userService.register();
    }
}
