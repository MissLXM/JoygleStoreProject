package cn.edu.mju.joygle.admin.controller;

import cn.edu.mju.joygle.admin.service.CategoryService;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.pojo.StoreCategory;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: CategoryController
 * Package: cn.edu.mju.joygle.admin.controller
 * Description: 类别管理控制层
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--13:00
 */
@RestController
@RequestMapping("/admin/category")
@Tag(name = "CategoryController", description = "类别管理服务")
public class CategoryController {

    @Setter(onMethod_ = @Autowired)
    private CategoryService categoryService;

    @GetMapping("/show/{currentPage}/{pageSize}/{keyword}")
    @Tag(name = "categoryInfoShow", description = "类别展示")
    public Result categoryInfoShow(
            @PathVariable("currentPage") Integer currentPage,
            @PathVariable("pageSize") Integer pageSize,
            @PathVariable("keyword") String keyword) {
        return categoryService.categoryInfoShow(currentPage, pageSize, keyword);
    }

    @PostMapping("/save")
    @Tag(name = "categoryInfoSave", description = "类别保存")
    public Result categoryInfoSave(@RequestBody @Validated StoreCategory category, BindingResult result) {
        if (result.hasErrors()) {
            return Result.fail().message("保存参数不合法");
        }
        return categoryService.categoryInfoSave(category);
    }

    @PutMapping("/update")
    @Tag(name = "categoryInfoUpdate", description = "类别修改")
    public Result categoryInfoUpdate(@RequestBody @Validated StoreCategory category, BindingResult result) {
        if (result.hasErrors()) {
            return Result.fail().message("修改参数不合法");
        }
        return categoryService.categoryInfoUpdate(category);
    }

    @DeleteMapping("/delete/{categoryId}")
    @Tag(name = "categoryInfoDelete", description = "类别删除")
    public Result categoryInfoDelete(@PathVariable("categoryId") Integer categoryId) {
        return categoryService.categoryInfoDelete(categoryId);
    }
}
