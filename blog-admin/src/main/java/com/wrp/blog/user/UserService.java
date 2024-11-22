package com.wrp.blog.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wrp.blog.user.param.UpdateUser;
import com.wrp.blog.user.param.UserLoginByPassword;
import com.wrp.blog.user.param.UserLoginByPhone;
import com.wrp.blog.user.param.UserRegister;
import com.wrp.blog.user.vo.UserInfo;

/**
 * @author wrp
 * @since 2024-11-19 21:25
 **/
public interface UserService extends IService<UserEntity> {
    Long register(UserRegister userRegister);

    UserInfo login(UserLoginByPassword userLoginByPassword);

    UserInfo login(UserLoginByPhone userLoginByPhone);

    UserEntity userDetail();

    void updateOptionInfo(UpdateUser updateUser);
}
