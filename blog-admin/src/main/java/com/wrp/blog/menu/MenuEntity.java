package com.wrp.blog.menu;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wrp.blog.common.BaseEntity;
import com.wrp.blog.common.dict.MenuType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 菜单
 * @author wrp
 * @since 2025-01-01 10:36
 **/
@TableName(value = "menu", autoResultMap = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuEntity extends BaseEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long parentId;
    @NotEmpty
    private String name;
    @NotNull
    private MenuType type;
    private String url;
    @JsonIgnore
    private Integer sort;

    @TableField(exist = false)
    private List<MenuEntity> children;
}
