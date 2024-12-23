package com.wrp.blog.file;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wrp.blog.common.BaseEntity;
import com.wrp.blog.common.dict.FileType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 文件
 * @author wrp
 * @since 2024-11-20 22:55
 **/
@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("file")
public class FileEntity extends BaseEntity {
    private Long userId;
    private String url;
    private String fileName;
    /**
     * byte
     */
    private long size;
    private FileType type;
}
