package com.wrp.blog.file;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wrp.blog.common.dict.ResultCode;
import com.wrp.blog.common.exception.FileException;
import com.wrp.blog.common.utils.FileUtils;
import com.wrp.blog.user.UserInfoContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author wrp
 * @since 2024-11-20 22:59
 **/
@Slf4j
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileEntity> implements FileService {

    @Value("${store.path}")
    private String store;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long upload(MultipartFile file) {
        FileEntity entity = new FileEntity()
                .setFileName(file.getOriginalFilename())
                .setUrl(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + File.separator + UUID.randomUUID())
                .setSize(file.getSize())
                .setUserId(UserInfoContext.get().getId());
        doUpload(file, entity.getUrl());
        try(InputStream is = Files.newInputStream(Paths.get(store, entity.getUrl()))) {
            entity.setType(FileUtils.resolveFileType(is));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        save(entity);
        return entity.getId();
    }

    @Override
    public ResponseEntity<Resource> download(Long id) {
        FileEntity fileEntity = getById(id);
        if(fileEntity == null) {
            throw FileException.of(ResultCode.RESOURCE_NOT_FOUND);
        }

        Resource resource = new FileSystemResource(Paths.get(store, fileEntity.getUrl()));
        if (resource.exists()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +
                            URLEncoder.encode(fileEntity.getFileName(), StandardCharsets.UTF_8))
                    .body(resource);
        }
        throw FileException.of(ResultCode.FILE_NOT_EXIST);
    }

    private void doUpload(MultipartFile file, String relativePath) {
        Path path = Paths.get(store, relativePath.substring(0, relativePath.lastIndexOf(File.separator)));
        if(!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                Files.copy(file.getInputStream(), Paths.get(store, relativePath));
            } catch (IOException e) {
                log.error("文件上传失败，{}", e.getMessage(), e);
                throw FileException.of(ResultCode.IO_ERROR);
            }
        }
    }
}
