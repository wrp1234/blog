package com.wrp.blog.common.exception;

import com.wrp.blog.common.dict.ResultCode;
import com.wrp.blog.common.result.Result;
import com.wrp.blog.common.result.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.stream.Collectors;

/**
 * @author wrp
 * @since 2024-11-19 22:14
 **/
@Slf4j
@RestControllerAdvice
public class ExceptionsControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new Result<>(ResultCode.ARGS_ERROR.getCode(), e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage).collect(Collectors.joining(";")), null);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public Result<String> handleNoResourceFoundException(NoResourceFoundException e) {
        log.warn("无效资源：{}", e.getMessage());
        return ResultUtils.error(ResultCode.RESOURCE_NOT_FOUND);
    }

    @ExceptionHandler(SystemException.class)
    public Result<String> handleBusinessException(SystemException e) {
        return ResultUtils.error(e.getResultCode());
    }

    @ExceptionHandler(Exception.class)
    public Result<String> handle(Exception e) {
        log.error("服务器内部错误信息：{}", e.getMessage(), e);
        return ResultUtils.error(ResultCode.ERROR);
    }
}
