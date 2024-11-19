package com.wrp.blog.common.result;

import com.wrp.blog.common.dict.ResultCode;

/**
 * @author wrp
 * @since 2024-11-19 21:27
 **/
public class ResultUtils {

    public static Result<Void> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return getResultInstance(ResultCode.SUCCESS.getCode(), null, data);
    }

    public static <T> Result<T> error(ResultCode resultCode) {
        return getResultInstance(resultCode.getCode(), resultCode.getDescription(), null);
    }

    private static <T> Result<T> getResultInstance(int code, String msg, T data) {
        return new Result<>(code, msg, data);
    }
}
