package com.wrp.blog.user.param;

import lombok.Data;

/**
 * @author wrp
 * @since 2024-11-19 21:45
 **/
@Data
public class UserLoginByPassword {
    private String username;
    private String password;
}
