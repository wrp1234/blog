package com.wrp.blog.article;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wrp.blog.article.param.ArticleQuery;
import com.wrp.blog.article.param.PublishArticle;
import com.wrp.blog.article.vo.ArticleVo;
import com.wrp.blog.common.result.Result;
import com.wrp.blog.common.result.ResultUtils;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    public Result<ArticleEntity> get(@PathVariable("id") Long id) {
        return ResultUtils.success(articleService.getById(id));
    }

    @PostMapping("page")
    public Result<IPage<ArticleVo>> page(@RequestBody @Validated ArticleQuery query) {
        return ResultUtils.success(articleService.pageQuery(query));
    }
}
