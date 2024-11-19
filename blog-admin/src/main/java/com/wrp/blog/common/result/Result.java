package com.wrp.blog.common.result;

import lombok.Getter;

/**
 * response data
 * @author wrp
 * @since 2024-11-19 21:27
 **/
@Getter
public class Result<T> {
    private final int code;
    private final String message;
    private final T data;
    private final long timestamp;

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }
}
