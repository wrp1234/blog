package com.wrp.blog.common.exception;

import com.wrp.blog.common.dict.ResultCode;

/**
 * @author wrp
 * @since 2024-11-20 23:21
 **/
public class FileException extends SystemException {
    public FileException(ResultCode resultCode) {
        super(resultCode);
    }

    public static FileException of(ResultCode resultCode) {
        return new FileException(resultCode);
    }
}
