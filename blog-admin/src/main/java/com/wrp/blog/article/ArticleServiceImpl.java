package com.wrp.blog.article;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wrp.blog.article.param.ArticleQuery;
import com.wrp.blog.article.param.PublishArticle;
import com.wrp.blog.article.vo.ArticleVo;
import com.wrp.blog.catalog.CatalogEntity;
import com.wrp.blog.catalog.CatalogService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author wrp
 * @since 2024-11-22 23:50
 **/
@Service
@AllArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleEntity> implements ArticleService {

    private CatalogService catalogService;

    @Override
    public Long publish(PublishArticle publishArticle) {
        ArticleEntity entity = new ArticleEntity();
        BeanUtils.copyProperties(publishArticle, entity);
        // 没有定义摘要，则默认使用正文前256个字符作为摘要内容
        if(!StringUtils.hasText(publishArticle.getAbstractContent())) {
            entity.setAbstractContent(publishArticle.getAbstractContent()
                    .substring(0, Math.min(256, publishArticle.getAbstractContent().length())));
        }

        if(Objects.nonNull(publishArticle.getCatalogId())) {
            CatalogEntity catalog = catalogService.getById(publishArticle.getCatalogId());
            if(Objects.isNull(catalog)) {
                // 清理无效目录
                entity.setCatalogId(null);
            } else {
                // async TODO 存在分类中记录的文章数量不一致的问题
                catalogService.incrementArticleCount(catalog);
            }
        }
        save(entity);
        return entity.getId();
    }

    @Override
    public IPage<ArticleVo> pageQuery(ArticleQuery query) {
        Page<ArticleEntity> page = page(new Page<>(query.getPageNo(), query.getPageSize()),
                new LambdaQueryWrapper<ArticleEntity>()
                        .eq(StringUtils.hasText(query.getTitle()), ArticleEntity::getTitle, query.getTitle())
                        .orderByDesc(ArticleEntity::getUpdateTime)
        );
        Page<ArticleVo> res = new Page<>();
        BeanUtils.copyProperties(page, res);
        res.setRecords(page.getRecords().stream().map(e-> {
            ArticleVo vo = new ArticleVo();
            BeanUtils.copyProperties(e, vo);
            return vo;
        }).toList());
        return res;
    }
}
