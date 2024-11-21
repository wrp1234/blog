package com.wrp.blog.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wrp.blog.api.CaptchaService;
import com.wrp.blog.common.dict.ResultCode;
import com.wrp.blog.common.exception.UserException;
import com.wrp.blog.common.utils.jwt.JwtUtils;
import com.wrp.blog.user.param.UserLoginByPassword;
import com.wrp.blog.user.param.UserLoginByPhone;
import com.wrp.blog.user.param.UserRegister;
import com.wrp.blog.user.vo.UserInfo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author wrp
 * @since 2024-11-19 21:25
 **/
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    private JwtUtils jwtUtils;
    private CaptchaService captchaService;


    @Override
    public Long register(UserRegister userRegister) {
        checkUniqOfUsernameAndPhone(userRegister);
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userRegister, userEntity);
        save(userEntity);
        return userEntity.getId();
    }

    private void checkUniqOfUsernameAndPhone(@NotNull UserRegister userRegister) {
        if(exists(new LambdaQueryWrapper<UserEntity>().eq(UserEntity::getUsername, userRegister.getUsername()))) {
            throw UserException.of(ResultCode.USERNAME_USED);
        }
        if(exists(new LambdaQueryWrapper<UserEntity>().eq(UserEntity::getPhone, userRegister.getPhone()))) {
            throw UserException.of(ResultCode.PHONE_USED);
        }
    }

    @Override
    public UserInfo login(UserLoginByPassword userLoginByPassword) {
        captchaService.verify(userLoginByPassword.getKey(), userLoginByPassword.getCode());
        UserEntity userEntity = getOne(new LambdaQueryWrapper<UserEntity>()
                .eq(UserEntity::getUsername, userLoginByPassword.getUsername())
                .eq(UserEntity::getPassword, userLoginByPassword.getPassword()));
        if(userEntity == null){
            throw UserException.of(ResultCode.USERNAME_PASSWORD_ERROR);
        }

        return doLogin(userEntity);
    }

    @Override
    public UserInfo login(UserLoginByPhone userLoginByPhone) {
        // TODO 实现手机号登录
        return null;
    }

    public UserInfo doLogin(UserEntity userEntity){
        UserInfo userInfo = new UserInfo().setId(userEntity.getId()).setUsername(userEntity.getUsername());
        userInfo.setToken(jwtUtils.createJWT(userInfo));
        return userInfo;
    }

    @Override
    public UserEntity userDetail() {
        return getById(UserInfoContext.get().getId());
    }
}
