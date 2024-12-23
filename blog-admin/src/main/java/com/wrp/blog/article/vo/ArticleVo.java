package com.wrp.blog.article.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wrp
 * @since 2024-12-23 22:38
 **/
@Data
public class ArticleVo {
    private Long id;
    private String title;
    /**
     * 摘要内容
     */
    private String abstractContent;
    private LocalDateTime createTime;
    private Long photoId;
}
