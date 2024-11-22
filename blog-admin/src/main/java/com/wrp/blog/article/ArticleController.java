package com.wrp.blog.article;

import com.wrp.blog.article.param.PublishArticle;
import com.wrp.blog.common.result.Result;
import com.wrp.blog.common.result.ResultUtils;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wrp
 * @since 2024-11-22 23:52
 **/
@RestController
@RequestMapping("article")
@AllArgsConstructor
public class ArticleController {

    private ArticleService articleService;

    @PostMapping
    public Result<String> publish(@RequestBody @Validated PublishArticle publishArticle) {
        return ResultUtils.success(articleService.publish(publishArticle).toString());
    }
}
