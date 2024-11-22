package com.wrp.blog.user;

import com.wrp.blog.common.result.Result;
import com.wrp.blog.common.result.ResultUtils;
import com.wrp.blog.user.param.UpdateUser;
import com.wrp.blog.user.param.UserLoginByPassword;
import com.wrp.blog.user.param.UserLoginByPhone;
import com.wrp.blog.user.param.UserRegister;
import com.wrp.blog.user.vo.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author wrp
 * @since 2024-11-19 21:26
 **/
@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("register")
    public Result<String> register(@RequestBody @Validated UserRegister userRegister) {
        return ResultUtils.success(userService.register(userRegister).toString());
    }

    @PostMapping("login/pwd")
    public Result<UserInfo> login(@RequestBody @Validated UserLoginByPassword userLoginByPassword) {
        return ResultUtils.success(userService.login(userLoginByPassword));
    }

    @PostMapping("login/phone")
    public Result<UserInfo> login(@RequestBody @Validated UserLoginByPhone userLoginByPhone) {
        return ResultUtils.success(userService.login(userLoginByPhone));
    }

    @GetMapping("detail")
    public Result<UserEntity> detail() {
        return ResultUtils.success(userService.userDetail());
    }

    @PutMapping
    public Result<Void> updateOptionInfo(@RequestBody @Validated UpdateUser updateUser) {
        userService.updateOptionInfo(updateUser);
        return ResultUtils.success();
    }

}
