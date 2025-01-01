package com.wrp.blog.common.dict;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型
 * @author wrp
 * @since 2025-01-01 10:39
 **/
@Getter
@AllArgsConstructor
public enum MenuType implements BaseEnum {
    CATALOG(1, "目录"),
    MENU(2, "菜单"),
    ;

    @EnumValue
    @JsonValue
    private final int code;
    private final String description;
}
