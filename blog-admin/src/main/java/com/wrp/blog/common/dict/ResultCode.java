package com.wrp.blog.common.dict;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应码 10xxx、90xxx
 * 用户模块 20xxx
 * @author wrp
 * @since 2024-11-19 21:29
 **/
@Getter
@AllArgsConstructor
public enum ResultCode implements BaseEnum {
    SUCCESS(10000, "成功"),
    USERNAME_PASSWORD_ERROR(20001, "用户名或密码错误"),
    USERNAME_USED(20002,"用户名已被使用"),
    PHONE_USED(20003,"手机号已被使用"),
    IO_ERROR(30001, "文件无法上传"),
    FILE_EMPTY_CONTENT(30002, "文件内容是空的"),
    NOT_SUPPORT_FILE_TYPE(30003, "不支持的文件格式"),
    FILE_NOT_EXIST(30004, "文件不存在"),
    RESOURCE_NOT_FOUND(99001, "NOT FOUND"),
    MISS_TOKEN(99002, "miss token"),
    INVALID_TOKEN(99003, "invalid token"),
    ERROR(99999, "服务内部异常"),
    ;
    private final int code;
    private final String description;
}
