package cn.edu.mju.joygle.orders.service;

import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.param.orders.MuchProductParam;


/**
 * ClassName: OrdersService
 * Package: cn.edu.mju.joygle.orders.service
 * Description: 订单业务接口
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/30--23:35
 */
public interface OrdersService {

    /**
     * 订单生成后展示
     * @param authorization 认证信息
     * @param userId 用户ID
     * @return 结果集
     */
    Result ordersShow(String authorization,Integer userId);

    /**
     * 订单生成前展示
     * @param authorization 认证信息
     * @param userId 用户ID
     * @param muchProductParam 商品数量和商品信息
     * @return 结果集
     */
    Result orderShowPrice(String authorization, Integer userId, MuchProductParam muchProductParam);

    /**
     * 商品订单生成
     * @param authorization 认证信息
     * @param userId 用户ID
     * @param muchProductParam 商品参数
     * @return 结果集
     */
    Result muchProductOrderSave(String authorization, Integer userId, MuchProductParam muchProductParam);
}
