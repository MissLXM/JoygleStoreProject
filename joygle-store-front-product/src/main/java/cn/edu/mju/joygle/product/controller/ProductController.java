package cn.edu.mju.joygle.product.controller;

import cn.edu.mju.joygle.common.core.constants.Constants;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.param.product.ProductIdsParam;
import cn.edu.mju.joygle.common.entity.param.product.SearchProductParam;
import cn.edu.mju.joygle.product.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: ProductController
 * Package: cn.edu.mju.joygle.product.controller
 * Description: 商品控制层
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/28--12:49
 */
@Slf4j
@RestController
@RequestMapping("/product")
@Tag(name = "ProductController",description = "商品控制层")
public class ProductController {

    @Setter(onMethod_ = @Autowired)
    private ProductService productService;

    @GetMapping("/hotProductShow/{currentPage}/{pageSize}")
    @Tag(name = "hotProductShow", description = "首页热门商品展示")
    public Result hotProductShow(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        // 如果前端没有传入当前页码和页面显示数量,默认为1、20
        if (currentPage == 0){ currentPage = Constants.CURRENT_PAGE;}
        if (pageSize == 0){ pageSize = Constants.PAGE_SIZE;}
        return productService.hotProductShow(currentPage, pageSize);
    }


    @GetMapping("/totalProductShow/{currentPage}/{pageSize}")
    @Tag(name = "totalProductShow", description = "全部商品展示")
    public Result totalProductShow(@RequestHeader("Authorization") String authorization, @PathVariable Integer currentPage,@PathVariable Integer pageSize) {
        // 如果前端没有传入当前页码和页面显示数量,默认为1、20
        if (currentPage == 0){ currentPage = Constants.CURRENT_PAGE;}
        if (pageSize == 0){ pageSize = Constants.PAGE_SIZE;}

        if (authorization != null) {
            return productService.totalProductShow(currentPage, pageSize);
        }
        return Result.fail().message("token未携带或已过期");
    }

    @GetMapping("/levelCategoryProductShow/{categoryId}/{currentPage}/{pageSize}")
    @Tag(name = "levelCategoryProductShow", description = "类别商品展示")
    public Result levelCategoryProductShow(@RequestHeader("Authorization") String authorization,
                                           @PathVariable Integer categoryId,
                                           @PathVariable Integer currentPage,
                                           @PathVariable Integer pageSize) {
        // 如果前端没有传入当前页码和页面显示数量,默认为1、20
        if (currentPage == 0){ currentPage = Constants.CURRENT_PAGE;}
        if (pageSize == 0){ pageSize = Constants.PAGE_SIZE;}

        if (authorization != null) {
            return productService.levelCategoryProductShow(authorization ,categoryId, currentPage, pageSize);
        }
        return Result.fail().message("token未携带或已过期");
    }

    @PostMapping("/searchProductShow")
    @Tag(name = "searchProductShow", description = "搜索商品展示")
    public Result searchProductShow(@RequestHeader("Authorization") String authorization, @RequestBody SearchProductParam productParam) {
        // 如果前端没有传入当前页码和页面显示数量,默认为1、20
        if (productParam.getCurrentPage() == 0) {
            productParam.setCurrentPage(Constants.CURRENT_PAGE);
        }
        if (productParam.getPageSize() == 0) {
            productParam.setPageSize(Constants.PAGE_SIZE);
        }

        if (authorization != null) {
            return productService.searchProductShow(productParam.getKeyword(),
                    productParam.getCurrentPage(),
                    productParam.getPageSize());
        }
        return Result.fail().message("token未携带或已过期");
    }

    @GetMapping("/productInfoShow/{productId}")
    @Tag(name = "productInfoShow", description = "商品详情展示")
    public Result productInfoShow(@RequestHeader("Authorization") String authorization,@PathVariable Integer productId) {
        if (authorization != null) {
            return productService.productInfoShow(productId);
        }
        return Result.fail().message("token未携带或已过期");
    }


    @GetMapping("/selectByProductIds")
    @Tag(name = "selectByProductIds", description = "多商品ID查询商品")
    public Result selectByProductIds(@RequestHeader("Authorization") String authorization, @RequestBody ProductIdsParam productIdsParam) {
        if (authorization != null) {
            return productService.selectByProductIds(productIdsParam);
        }
        return Result.fail().message("token未携带或已过期");
    }
}


