package com.wrp.blog.file;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wrp
 * @since 2024-11-20 22:59
 **/
public interface FileService extends IService<FileEntity> {
    Long upload(MultipartFile file);

    ResponseEntity<Resource> download(Long id);
}
