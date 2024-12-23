package com.wrp.blog.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wrp.blog.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 用户
 * @author wrp
 * @since 2024-11-19 21:20
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("b_user")
public class UserEntity extends BaseEntity {
    private String username;
    private String password;
    private String phone;
    private LocalDate birthday;
    /**
     * 自我介绍
     */
    private String introduction;
    private Long photoId;
}
