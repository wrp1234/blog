package com.wrp.blog.user.param;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author wrp
 * @since 2024-11-19 21:39
 **/
@Data
public class UserRegister {
    @NotEmpty(message = "请填写用户名")
    private String username;
    @NotEmpty(message = "请填写密码")
    private String password;
    @NotEmpty(message = "请填写手机号码")
    private String phone;
    private LocalDate birthday;
    private String introduction;
    private String photo;
}
