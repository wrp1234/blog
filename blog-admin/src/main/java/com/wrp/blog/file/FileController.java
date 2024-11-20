package com.wrp.blog.file;

import com.wrp.blog.common.result.Result;
import com.wrp.blog.common.result.ResultUtils;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wrp
 * @since 2024-11-20 22:54
 **/
@RestController
@RequestMapping("file")
@AllArgsConstructor
public class FileController {

    private FileService fileService;

    @PostMapping
    public Result<String> upload(@RequestPart("file") MultipartFile file) {
        return ResultUtils.success(fileService.upload(file).toString());
    }

    @GetMapping("{id}")
    public ResponseEntity<Resource> download(@PathVariable("id") Long id) {
        return fileService.download(id);
    }
}
