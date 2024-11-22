package com.wrp.blog.catalog;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wrp.blog.catalog.param.CreateCatalog;
import com.wrp.blog.catalog.vo.CatalogVo;
import com.wrp.blog.user.UserInfoContext;
import com.wrp.blog.user.vo.UserInfo;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author wrp
 * @since 2024-11-22 23:51
 **/
@Service
public class CatalogServiceImpl extends ServiceImpl<CatalogMapper, CatalogEntity> implements CatalogService {
    @Override
    public Long create(CreateCatalog catalog) {
        UserInfo userInfo = UserInfoContext.get();
        CatalogEntity entity = getOne(new LambdaQueryWrapper<CatalogEntity>()
                .eq(CatalogEntity::getUserId, userInfo.getId())
                .eq(CatalogEntity::getName, catalog.getName()));
        if(Objects.isNull(entity)) {
            entity = new CatalogEntity();
            BeanUtils.copyProperties(catalog, entity);
            entity.setUserId(userInfo.getId());
            save(entity);
        }
        return entity.getId();
    }

    @Override
    public List<CatalogVo> listOfUser() {
        return list(new LambdaQueryWrapper<CatalogEntity>()
                .eq(CatalogEntity::getUserId, UserInfoContext.get().getId())
                .orderByDesc(CatalogEntity::getArticleCount)
                .orderByDesc(CatalogEntity::getCreateTime)).stream()
                .map(this::convert)
                .toList();
    }

    @Async
    @Override
    public void incrementArticleCount(@NotNull CatalogEntity catalog) {
        catalog.setArticleCount(catalog.getArticleCount() + 1);
        updateById(catalog);
    }

    private CatalogVo convert(CatalogEntity entity) {
        CatalogVo vo = new CatalogVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
