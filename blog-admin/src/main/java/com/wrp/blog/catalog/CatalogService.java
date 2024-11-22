package com.wrp.blog.catalog;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wrp.blog.catalog.param.CreateCatalog;
import com.wrp.blog.catalog.vo.CatalogVo;

import java.util.List;

/**
 * @author wrp
 * @since 2024-11-22 23:50
 **/

public interface CatalogService extends IService<CatalogEntity> {

    Long create(CreateCatalog catalog);

    List<CatalogVo> listOfUser();

    void incrementArticleCount(CatalogEntity catalog);
}
