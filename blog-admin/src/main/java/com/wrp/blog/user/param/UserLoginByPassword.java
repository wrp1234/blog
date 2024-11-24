package com.wrp.blog.user.param;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @author wrp
 * @since 2024-11-19 21:45
 **/
@Data
public class UserLoginByPassword {
    @NotEmpty(message = "请填写用户名")
    private String username;
    private String password;
    private String key;
    private String code;
}
