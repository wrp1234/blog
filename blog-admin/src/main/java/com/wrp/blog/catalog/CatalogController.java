package com.wrp.blog.catalog;

import com.wrp.blog.catalog.param.CreateCatalog;
import com.wrp.blog.catalog.vo.CatalogVo;
import com.wrp.blog.common.result.Result;
import com.wrp.blog.common.result.ResultUtils;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wrp
 * @since 2024-11-23 00:19
 **/
@RestController
@RequestMapping("catalog")
@AllArgsConstructor
public class CatalogController {

    private CatalogService catalogService;

    @PostMapping
    public Result<String> create(@RequestBody @Validated CreateCatalog catalog) {
        return ResultUtils.success(catalogService.create(catalog).toString());
    }

    @GetMapping
    public Result<List<CatalogVo>> list() {
        return ResultUtils.success(catalogService.listOfUser());
    }
}
