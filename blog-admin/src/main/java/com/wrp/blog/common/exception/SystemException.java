package com.wrp.blog.common.exception;

import com.wrp.blog.common.dict.ResultCode;
import lombok.Getter;

/**
 * @author wrp
 * @since 2024-11-19 22:16
 **/
@Getter
public class SystemException extends RuntimeException {

    private final ResultCode resultCode;

    public SystemException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public static SystemException of(ResultCode resultCode) {
        return new SystemException(resultCode);
    }
}
