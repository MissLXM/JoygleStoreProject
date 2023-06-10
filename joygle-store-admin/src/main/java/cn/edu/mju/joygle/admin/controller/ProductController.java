package cn.edu.mju.joygle.admin.controller;

import cn.edu.mju.joygle.admin.service.ProductService;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.pojo.StoreProduct;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: ProductController
 * Package: cn.edu.mju.joygle.admin.controller
 * Description: 商品管理控制层
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--13:01
 */
@RestController
@RequestMapping("/admin/product")
@Tag(name = "ProductController", description = "商品服务管理")
public class ProductController {

    @Setter(onMethod_ = @Autowired)
    private ProductService productService;

    @GetMapping("/show/{currentPage}/{pageSize}/{keyword}")
    @Tag(name = "productInfoShow", description = "商品展示")
    public Result productInfoShow(
            @PathVariable("currentPage") Integer currentPage,
            @PathVariable("pageSize") Integer pageSize,
            @PathVariable("keyword") String keyword) {
        return productService.productInfoShow(currentPage,pageSize,keyword);
    }

    @PostMapping("/save")
    @Tag(name = "productInfoSave", description = "商品保存")
    public Result productInfoSave(@RequestBody @Validated StoreProduct product, BindingResult result) {
        if (result.hasErrors()) {
            return Result.fail().message("保存参数不合法");
        }
        return productService.productInfoSave(product);
    }

    @PutMapping("/update")
    @Tag(name = "productInfoUpdate", description = "商品修改")
    public Result productInfoUpdate(@RequestBody @Validated StoreProduct product, BindingResult result) {
        if (result.hasErrors()) {
            return Result.fail().message("修改参数不合法");
        }
        return productService.productInfoUpdate(product);
    }

    @DeleteMapping("/delete/{productId}")
    @Tag(name = "productInfoDelete", description = "商品删除")
    public Result productInfoDelete(@PathVariable("productId") Integer productId) {
        return productService.productInfoDelete(productId);
    }

    @DeleteMapping("/deleteByProductIds")
    @Tag(name = "deleteByProductIds", description = "多商品删除")
    public Result deleteByProductIds(@RequestParam("productIds") List<Integer> productIds) {
        return productService.deleteByProductIds(productIds);
    }
}
