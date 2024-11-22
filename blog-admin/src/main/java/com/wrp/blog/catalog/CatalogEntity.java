package com.wrp.blog.catalog;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wrp.blog.common.config.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 目录
 * @author wrp
 * @since 2024-11-22 23:48
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("catalog")
public class CatalogEntity extends BaseEntity {

    private Long userId;
    private String name;
    private String description;
    /**
     * 文章数量
     */
    private Integer articleCount;
}
