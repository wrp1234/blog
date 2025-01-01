package com.wrp.blog.menu;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wrp
 * @since 2025-01-01 10:42
 **/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuService {

    @Override
    public List<MenuEntity> listWithChildren() {
        List<MenuEntity> list = list(new LambdaQueryWrapper<MenuEntity>()
                .orderByDesc(MenuEntity::getOrder));
        Map<Long, MenuEntity> map = list.stream()
                .collect(Collectors.toMap(MenuEntity::getId, e -> e));
        list.forEach(m -> {
            if(m.getParentId() != null) {
                MenuEntity parent = map.get(m.getParentId());
                if(CollectionUtils.isEmpty(parent.getChildren())) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(m);
            }
        });
        return list.stream().filter(m -> m.getParentId() == null).toList();
    }
}
