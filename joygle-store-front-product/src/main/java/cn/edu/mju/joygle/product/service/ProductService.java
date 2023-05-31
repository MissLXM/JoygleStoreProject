package cn.edu.mju.joygle.product.service;

import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.param.product.ProductIdsParam;

/**
 * ClassName: ProductService
 * Package: cn.edu.mju.joygle.product.service
 * Description: 商品服务接口
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/28--12:34
 */
public interface ProductService {

    /**
     * 热门商品展示(按照销售量降序排列)
     * @param currentPage 当前页码
     * @param pageSize 每页显示多少条数据
     * @return 结果集
     */
    Result hotProductShow(Integer currentPage,Integer pageSize);

    /**
     * 全部商品展示(默认展示)
     * @param currentPage 当前页码
     * @param pageSize 每页显示多少条数据
     * @return 结果集
     */
    Result totalProductShow(Integer currentPage,Integer pageSize);

    /**
     * 商品详情展示
     * @param productId 商品ID
     * @return 结果集
     */
    Result productInfoShow(Integer productId);

    /**
     * 根据搜索的结果展示数据
     * @param keyword 关键字搜索
     * @param currentPage 当前页码
     * @param pageSize 每页显示多少条数据
     * @return
     */
    Result searchProductShow(String keyword,Integer currentPage,Integer pageSize);

    /**
     * 类别商品展示(一级、二级)
     * @param currentPage 当前页码
     * @param pageSize 每页显示多少条数据
     * @return
     */
    Result levelCategoryProductShow(String authentication,Integer categoryId,Integer currentPage,Integer pageSize);

    /**
     * 多商品ID查询
     * @param productIdsParam 多商品ID参数
     * @return 结果集
     */
    Result selectByProductIds(ProductIdsParam productIdsParam);

}
