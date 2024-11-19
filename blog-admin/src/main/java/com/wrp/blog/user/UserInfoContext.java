package com.wrp.blog.user;

import com.wrp.blog.user.vo.UserInfo;
import org.springframework.core.NamedThreadLocal;

/**
 * 请求上下文中的用户信息
 * @author wrp
 * @since 2024-11-19 22:26
 **/
public class UserInfoContext {
    private static final ThreadLocal<UserInfo> userThreadLocal = NamedThreadLocal.withInitial("userThreadLocal", ()-> null);

    /**
     * 获取用户
     * @return 当前请求的用户
     */
    public static UserInfo get() {
        return userThreadLocal.get();
    }

    /**
     * 设置用户
     * @param user 当前请求的用户
     */
    public static void set(UserInfo user) {
        userThreadLocal.set(user);
    }

    /**
     * 移除用户信息，防止内存泄露
     */
    public static void remove() {
        userThreadLocal.remove();
    }
}
