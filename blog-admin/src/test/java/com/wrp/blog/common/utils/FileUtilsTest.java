package com.wrp.blog.common.utils;

import com.wrp.blog.common.dict.FileType;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wrp
 * @since 2024-11-20 23:54
 **/
public class FileUtilsTest {


    @Test
    public void testResolveFileType() throws IOException {
        FileType fileType = FileUtils.resolveFileType(new ClassPathResource("Image_1726272315132.jpg")
                .getInputStream());
        assertEquals(FileType.PNG, fileType);
    }
}