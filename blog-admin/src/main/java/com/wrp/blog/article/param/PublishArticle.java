package com.wrp.blog.article.param;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author wrp
 * @since 2024-11-22 23:57
 **/
@Data
public class PublishArticle {
    @NotEmpty(message = "标题未填写")
    @Length(min = 5, max = 128, message = "标题长度为5至128个字符")
    private String title;
    /**
     * 摘要内容
     */
    @Length(min = 5, max = 256, message = "摘要内容长度为5至256个字符")
    private String abstractContent;
    @NotEmpty(message = "内容未填写")
    @Length(min = 20, message = "内容过短")
    private String content;
    private Long photoId;
    private Long catalogId;
}
