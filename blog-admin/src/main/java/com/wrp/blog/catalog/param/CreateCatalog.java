package com.wrp.blog.catalog.param;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @author wrp
 * @since 2024-11-23 00:20
 **/
@Data
public class CreateCatalog {
    @NotEmpty(message = "目录名称未填写")
    private String name;
    private String description;
}
