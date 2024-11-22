package com.wrp.blog.catalog.vo;

import lombok.Data;

/**
 * @author wrp
 * @since 2024-11-23 00:28
 **/
@Data
public class CatalogVo {
    private Long id;
    private String name;
    private String description;
    /**
     * 文章数量
     */
    private Integer articleCount;
}
