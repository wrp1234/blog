package com.wrp.blog.common.exception;

import com.wrp.blog.common.dict.ResultCode;

/**
 * @author wrp
 * @since 2024-11-19 22:20
 **/
public class UserException extends SystemException {
    public UserException(ResultCode resultCode) {
        super(resultCode);
    }

    public static UserException of(ResultCode resultCode) {
        return new UserException(resultCode);
    }
}
