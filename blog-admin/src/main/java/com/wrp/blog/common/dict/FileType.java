package com.wrp.blog.common.dict;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wrp.blog.common.exception.FileException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author wrp
 * @since 2024-11-20 22:57
 **/
@Getter
@AllArgsConstructor
public enum FileType implements BaseEnum {
    JPG(1, "FFD8FF","JPG"),
    PNG(2, "89504E47","PNG"),
    ;

    @EnumValue
    @JsonValue
    private final int code;
    private final String prefixByte;
    private final String description;

    /**
     * 根据文件前缀获取文件类型
     * @param fileHeader 文件内容前缀16进制字符串
     * @return 文件内容
     */
    public static FileType of(String fileHeader) {
        return Arrays.stream(values()).filter(e->fileHeader.startsWith(e.getPrefixByte())).findFirst()
                .orElseThrow(()-> FileException.of(ResultCode.NOT_SUPPORT_FILE_TYPE));
    }
}
