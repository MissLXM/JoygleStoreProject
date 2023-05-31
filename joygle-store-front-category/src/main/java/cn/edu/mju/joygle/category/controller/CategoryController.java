package cn.edu.mju.joygle.category.controller;

import cn.edu.mju.joygle.category.service.CategoryService;
import cn.edu.mju.joygle.common.core.domain.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: CategoryController
 * Package: cn.edu.mju.joygle.category.controller
 * Description: 类别控制层
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/28--12:34
 */
@Slf4j
@RestController
@RequestMapping("/category")
@Tag(name = "CategoryController", description = "类别控制层")
public class CategoryController {

    @Setter(onMethod_ = @Autowired)
    private CategoryService categoryService;

    @GetMapping("/oneLevelCategoryShow")
    @Tag(name = "oneLevelCategoryShow", description = "展示所有一级类别")
    public Result oneLevelCategoryShow(@RequestHeader("Authorization") String authorization) {
        if (authorization != null) {
            return categoryService.oneLevelCategoryShow();
        }
        return Result.fail().message("token为空或过期");
    }

    @GetMapping("/twoLevelCategoryShow/{categoryParentId}")
    @Tag(name = "twoLevelCategoryShow", description = "根据一级类别ID查询二级类别")
    public Result twoLevelCategoryShow(@RequestHeader("Authorization") String authorization, @PathVariable("categoryParentId") Integer categoryParentId) {
        if (authorization != null) {
            return categoryService.twoLevelCategoryShow(categoryParentId);
        }
        return Result.fail().message("token为空或过期");
    }
}
