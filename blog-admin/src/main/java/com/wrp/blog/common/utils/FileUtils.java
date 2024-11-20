package com.wrp.blog.common.utils;

import com.wrp.blog.common.dict.FileType;
import com.wrp.blog.common.dict.ResultCode;
import com.wrp.blog.common.exception.FileException;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author wrp
 * @since 2024-11-20 23:26
 **/
public class FileUtils {

    /**
     * 解析输入流的文件类型
     * @param is 文件流
     * @return 文件类型
     * @throws IOException io异常
     */
    public static FileType resolveFileType(InputStream is) throws IOException {
        String fileHeader = getFileHeader(is);
        if(!StringUtils.hasText(fileHeader)) {
            throw FileException.of(ResultCode.FILE_EMPTY_CONTENT);
        }
        return FileType.of(fileHeader);
    }

    /**
     * 获取文件头
     *
     * @param inputStream 输入流
     * @return 16 进制的文件投信息
     * @throws IOException io异常
     */
    private static String getFileHeader(InputStream inputStream) throws IOException {
        byte[] b = new byte[28];
        inputStream.read(b, 0, 28);
        return bytes2hex(b);
    }

    /**
     * 将字节数组转换成16进制字符串
     *
     * @param src 文件字节数组
     * @return 16进制字符串
     */
    private static String bytes2hex(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte b : src) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().toUpperCase();
    }
}
