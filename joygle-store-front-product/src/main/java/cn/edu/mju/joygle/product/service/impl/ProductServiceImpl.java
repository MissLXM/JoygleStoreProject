package cn.edu.mju.joygle.product.service.impl;

import cn.edu.mju.joygle.common.client.category.CategoryClient;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.dto.category.CategoryDto;
import cn.edu.mju.joygle.common.entity.pojo.StoreProduct;
import cn.edu.mju.joygle.common.entity.dto.product.ProductDto;
import cn.edu.mju.joygle.common.entity.param.product.ProductIdsParam;
import cn.edu.mju.joygle.product.mapper.ProductMapper;
import cn.edu.mju.joygle.product.service.ProductService;
import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ProductServiceImpl
 * Package: cn.edu.mju.joygle.product.service.impl
 * Description: 商品服务实现
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/28--12:34
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Setter(onMethod_ = @Autowired)
    private ProductMapper productMapper;

    @Setter(onMethod_ = @Autowired)
    private CategoryClient categoryClient;

    /**
     * 封装结果集
     * @param productIPage 最初查到到的分页信息
     * @param productDtos 回显商品集合
     * @param productDtoIpage 封装好的商品分页信息
     * @return
     */
    private Result resultEncapsulation(IPage<StoreProduct> productIPage,List<ProductDto> productDtos,IPage<ProductDto> productDtoIpage) {
        // 拷贝分页参数(带数据)
        BeanUtil.copyProperties(productIPage,productDtoIpage);

        // 封装商品数据
        productIPage.getRecords().forEach(product -> {
            ProductDto productDto = new ProductDto();
            BeanUtil.copyProperties(product, productDto);
            productDtos.add(productDto);
        });

        // 返回结果集
        productDtoIpage.setRecords(productDtos);
        return Result.ok(productDtoIpage).message("商品查询成功");
    }

    /**
     * 热门商品展示(按照销售量降序排列)
     * @param currentPage 当前页码
     * @param pageSize    每页显示多少条数据
     * @return 结果集
     */
    @Override
    public Result hotProductShow(Integer currentPage, Integer pageSize) {
        // 初始化数据
        IPage<StoreProduct> page = new Page<>(currentPage, pageSize);
        QueryWrapper<StoreProduct> wrapper = new QueryWrapper<>();

        // 设置条件按销售量降序排序
        wrapper.orderByDesc("product_sales");
        // 查询数据
        IPage<StoreProduct> productIPage = productMapper.selectPage(page, wrapper);
        // 判空
        if (productIPage.getRecords().size() == 0) {
            return Result.fail().message("未查询到商品");
        }

        // 封装返回结果
        List<ProductDto> productDtos = new ArrayList<>();
        IPage<ProductDto> productDtoIpage = new Page<>(currentPage,pageSize);

        return resultEncapsulation(productIPage,productDtos,productDtoIpage);
    }

    /**
     * 全部商品展示(默认展示)
     * @param currentPage 当前页码
     * @param pageSize    每页显示多少条数据
     * @return 结果集
     */
    @Override
    public Result totalProductShow(Integer currentPage, Integer pageSize) {
        // 初始化数据
        IPage<StoreProduct> page = new Page<>(currentPage, pageSize);

        // 查询数据
        IPage<StoreProduct> productIPage = productMapper.selectPage(page, null);

        // 判空
        if (productIPage.getRecords().size() == 0) {
            return Result.fail().message("未查询到商品");
        }

        // 封装返回结果
        List<ProductDto> productDtos = new ArrayList<>();
        IPage<ProductDto> productDtoIpage = new Page<>(currentPage,pageSize);

        return resultEncapsulation(productIPage,productDtos,productDtoIpage);
    }

    /**
     * 商品详情展示
     * @param productId 商品ID
     * @return 结果集
     */
    @Override
    public Result productInfoShow(Integer productId) {
        // 查询结果
        StoreProduct storeProduct = productMapper.selectById(productId);

        // 判空
        if (storeProduct == null) {
            return Result.fail().message("未查询到商品");
        }
        // 封装结果集
        ProductDto productDto = new ProductDto();
        BeanUtil.copyProperties(storeProduct, productDto);
        return Result.ok(productDto).message("查询成功");
    }

    /**
     * 根据搜索的结果展示数据
     * @param keyword     关键字搜索
     * @param currentPage 当前页码
     * @param pageSize    每页显示多少条数据
     * @return
     */
    @Override
    public Result searchProductShow(String keyword, Integer currentPage, Integer pageSize) {
        // 初始化数据
        LambdaQueryWrapper<StoreProduct> wrapper = new LambdaQueryWrapper<>();
        IPage<StoreProduct> page = new Page<>(currentPage,pageSize);

        // 设置条件
        wrapper.like(StoreProduct::getProductName,keyword);

        // 查询数据
        IPage<StoreProduct> productIPage = productMapper.selectPage(page, wrapper);

        // 判空
        if (productIPage.getRecords().size() == 0) {
            return Result.fail().message("未查询到商品");
        }

        // 封装返回结果
        List<ProductDto> productDtos = new ArrayList<>();
        IPage<ProductDto> productDtoIpage = new Page<>(currentPage,pageSize);

        return resultEncapsulation(productIPage,productDtos,productDtoIpage);
    }

    /**
     * 类别商品展示(一级、二级)
     * @param currentPage 当前页码
     * @param pageSize    每页显示多少条数据
     * @return
     */
    @Override
    public Result levelCategoryProductShow(String authorization,Integer categoryId, Integer currentPage, Integer pageSize) {
        // 初始化数据
        LambdaQueryWrapper<StoreProduct> wrapper = new LambdaQueryWrapper<>();
        IPage<StoreProduct> page = new Page<>(currentPage,pageSize);

        // 设置条件
        wrapper.eq(StoreProduct::getCategoryId, categoryId);

        // 查询结果
        IPage<StoreProduct> productIPage = productMapper.selectPage(page, wrapper);

        // 判空(二级类别找不到)
        if (productIPage.getRecords().size() == 0) {
            wrapper = new LambdaQueryWrapper<>();
            // 查询到的二级类别集合
            List<CategoryDto> categoryDtoMap = (List<CategoryDto>) categoryClient.twoLevelCategoryShow(authorization,categoryId).getData();
            List<Integer> categoryIds = new ArrayList<>();

            // 转换为List集合
            String jsonString = JSON.toJSONString(categoryDtoMap);
            List<CategoryDto> categoryDtos = JSONObject.parseArray(jsonString, CategoryDto.class);

            // 所有二级类别
            categoryDtos.forEach(categoryDto -> categoryIds.add(categoryDto.getCategoryId()));

            // 查询该个字段多个数值的数据
            wrapper.in(StoreProduct::getCategoryId,categoryIds);
            IPage<StoreProduct> productIPages = productMapper.selectPage(page, wrapper);

            if (productIPages.getRecords().size() == 0) {
                return Result.fail().message("未查询到商品");
            }

            // 封装返回结果
            List<ProductDto> productDtos = new ArrayList<>();
            IPage<ProductDto> productDtoIpage = new Page<>(currentPage,pageSize);

            return resultEncapsulation(productIPages,productDtos,productDtoIpage);
        }

        // 封装结果集返回
        List<ProductDto> productDtos = new ArrayList<>();
        IPage<ProductDto> productDtoIpage = new Page<>(currentPage,pageSize);

        return resultEncapsulation(productIPage,productDtos,productDtoIpage);
    }

    /**
     * 多商品ID查询
     * @param productIdsParam 多商品ID参数
     * @return 结果集
     */
    @Override
    public Result selectByProductIds(ProductIdsParam productIdsParam) {
        if (productIdsParam.getCurrentPage() == null) {
            productIdsParam.setCurrentPage(1);
        }
        if (productIdsParam.getPageSize() == null) {
            productIdsParam.setPageSize(1000);
        }
        // 初始化数据
        LambdaQueryWrapper<StoreProduct> wrapper = new LambdaQueryWrapper<>();
        IPage<StoreProduct> page = new Page<>(productIdsParam.getCurrentPage(), productIdsParam.getPageSize());

        // 设置条件
        wrapper.in(StoreProduct::getProductId, productIdsParam.getProductIds());

        // 查询
        IPage<StoreProduct> productIPage = productMapper.selectPage(page, wrapper);
        // 判空
        if (productIPage.getRecords().size() == 0) {
            return Result.fail().message("商品查询失败");
        }

        // 封装结果集
        List<ProductDto> productDtos = new ArrayList<>();
        IPage<ProductDto> productDtoIpage = new Page<>(productIdsParam.getCurrentPage(), productIdsParam.getPageSize());

        // 拷贝分页参数(带数据)
        BeanUtil.copyProperties(productIPage, productDtoIpage);

        // 封装商品数据
        productIPage.getRecords().forEach(product -> {
            ProductDto productDto = new ProductDto();
            BeanUtil.copyProperties(product, productDto);
            productDtos.add(productDto);
        });

        // 返回结果集
        return Result.ok(productDtos).message("商品查询成功");
    }

}
