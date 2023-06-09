package cn.edu.mju.joygle.admin.service.impl;

import cn.edu.mju.joygle.admin.mapper.ProductMapper;
import cn.edu.mju.joygle.admin.service.ProductService;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.pojo.StoreProduct;
import cn.edu.mju.joygle.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: ProductServiceImpl
 * Package: cn.edu.mju.joygle.admin.service.impl
 * Description: 商品业务实现
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--19:20
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Setter(onMethod_ = @Autowired)
    private ProductMapper productMapper;


    /**
     * 商品展示
     * @param currentPage 当前页码
     * @param pageSize    每页显示数
     * @param keyword     搜索关键字
     * @return 结果集
     */
    @Override
    public Result productInfoShow(Integer currentPage, Integer pageSize, String keyword) {
        // 初始化数据
        IPage<StoreProduct> ipage = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<StoreProduct> wrapper = new LambdaQueryWrapper<>();

        // 判断是否有搜索
        if ("null".equals(keyword)) { keyword = null; }
        if (StringUtils.isNotEmpty(keyword)) {
            wrapper.like(StoreProduct::getProductName, keyword);
        }

        // 查询
        IPage<StoreProduct> productIPage = productMapper.selectPage(ipage, wrapper);
        if (productIPage.getRecords().size() == 0) {
            return Result.fail().message("查询失败");
        }
        return Result.ok(productIPage).message("查询成功");
    }

    /**
     * 保存商品
     * @param product 商品信息
     * @return 结果集
     */
    @Override
    public Result productInfoSave(StoreProduct product) {
        // 保存
        int rows = productMapper.insert(product);
        // 判空返回
        return getResult(rows);
    }

    /**
     * 修改商品
     * @param product 商品信息
     * @return 结果集
     */
    @Override
    public Result productInfoUpdate(StoreProduct product) {
        // 修改
        int rows = productMapper.updateById(product);
        // 判空返回
        return getResult(rows);
    }

    /**
     * 删除商品
     * @param productId 商品ID
     * @return 结果集
     */
    @Override
    public Result productInfoDelete(Integer productId) {
        // 根据商品ID删除
        int rows = productMapper.deleteById(productId);
        // 判空返回
        return getResult(rows);
    }

    @Override
    public Result deleteByProductIds(List<Integer> productIds) {
        // 遍历删除
        for (Integer productId : productIds) {
            int rows = productMapper.deleteById(productId);
            if (rows == 0) {
                return Result.fail().message("商品ID为:" + productId + "的商品删除失败");
            }
        }
        return Result.ok().message("删除成功");
    }

    /**
     * 判空返回
     * @param rows 影响行数
     * @return 结果集
     */
    private static Result getResult(int rows) {
        // 是否保存成功
        if (rows == 0) {
            return Result.fail().message("失败");
        }
        return Result.ok().message("成功");
    }
}
