package cn.edu.mju.joygle.admin.service;

import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.pojo.StoreProduct;

import java.util.List;

/**
 * ClassName: ProductService
 * Package: cn.edu.mju.joygle.admin.service
 * Description: 商品业务接口
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--19:19
 */
public interface ProductService {

    /**
     * 商品展示
     * @param currentPage 当前页码
     * @param pageSize 每页显示数
     * @param keyword 搜索关键字
     * @return 结果集
     */
    Result productInfoShow(Integer currentPage, Integer pageSize,String keyword);

    /**
     * 保存商品
     * @param product 商品信息
     * @return 结果集
     */
    Result productInfoSave(StoreProduct product);

    /**
     * 修改商品
     * @param product 商品信息
     * @return 结果集
     */
    Result productInfoUpdate(StoreProduct product);

    /**
     * 删除商品
     * @param productId 商品ID
     * @return 结果集
     */
    Result productInfoDelete(Integer productId);

    /**
     * 删除多商品
     * @param productIds 多商品ID
     * @return 结果集
     */
    Result deleteByProductIds(List<Integer> productIds);
}
