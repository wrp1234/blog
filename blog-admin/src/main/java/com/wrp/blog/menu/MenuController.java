package com.wrp.blog.menu;

import com.wrp.blog.common.result.Result;
import com.wrp.blog.common.result.ResultUtils;
import com.wrp.blog.common.valid.annotaion.UpdateGroup;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wrp
 * @since 2025-01-01 10:43
 **/
@RestController
@RequestMapping("menu")
@AllArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public Result<Long> add(@RequestBody @Validated MenuEntity menu) {
        menuService.save(menu);
        return ResultUtils.success(menu.getId());
    }

    @GetMapping
    public Result<List<MenuEntity>> list() {
        return ResultUtils.success(menuService.listWithChildren());
    }

    @PutMapping
    public Result<Void> update(@RequestBody @Validated(UpdateGroup.class) MenuEntity menu) {
        menuService.updateById(menu);
        return ResultUtils.success();
    }

    @DeleteMapping("{id}")
    public Result<Void> delete(@PathVariable("id") Long id) {
        menuService.removeById(id);
        return ResultUtils.success();
    }
}
