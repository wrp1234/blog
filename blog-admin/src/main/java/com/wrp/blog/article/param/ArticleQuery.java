package com.wrp.blog.article.param;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @author wrp
 * @since 2024-12-23 22:35
 **/
@Data
public class ArticleQuery {
    @NotEmpty(message = "页码为空")
    @Min(value = 1, message = "页码最小为1")
    private Long pageNo;
    @NotEmpty(message = "页大小为空")
    @Max(value = 20, message = "页大小最大为20")
    @Min(value = 5, message = "页大小最小为5")
    private Long pageSize;

    private String title;
}
