package com.wrp.blog.common;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wrp.blog.common.valid.annotaion.UpdateGroup;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wrp
 * @since 2024-11-19 21:09
 **/
@Data
public class BaseEntity {
    @NotNull(groups = UpdateGroup.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @JsonIgnore
    @TableLogic
    private Integer deleted;
}
