package com.wrp.blog.article;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wrp.blog.article.param.PublishArticle;

/**
 * @author wrp
 * @since 2024-11-22 23:49
 **/
public interface ArticleService extends IService<ArticleEntity> {

    Long publish(PublishArticle publishArticle);
}
