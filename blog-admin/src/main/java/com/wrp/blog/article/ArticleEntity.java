package com.wrp.blog.article;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wrp.blog.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章
 * @author wrp
 * @since 2024-11-22 23:35
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("article")
public class ArticleEntity extends BaseEntity {

    private String title;
    /**
     * 摘要内容
     */
    private String abstractContent;
    private String content;
    private Long photoId;
    private Long userId;
    private Long catalogId;
}
