package com.wrp.blog.menu;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author wrp
 * @since 2025-01-01 10:41
 **/
public interface MenuService extends IService<MenuEntity> {
    List<MenuEntity> listWithChildren();
}
