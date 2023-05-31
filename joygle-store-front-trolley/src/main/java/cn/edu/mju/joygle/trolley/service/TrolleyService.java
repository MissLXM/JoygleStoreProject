package cn.edu.mju.joygle.trolley.service;

import cn.edu.mju.joygle.common.core.domain.Result;

/**
 * ClassName: TrolleyService
 * Package: cn.edu.mju.joygle.trolley.service
 * Description: 购物车业务接口
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/29--0:57
 */
public interface TrolleyService {

    /**
     * 展示用户购物车
     * @param authorization 认证信息
     * @param userId 用户ID
     * @return 结果集
     */
    Result showUserTrolley(String authorization,Integer userId);

    /**
     * 添加商品到购物车
     * @param authorization 认证信息
     * @param userId 用户ID
     * @param productId 商品ID
     * @return 结果集
     */
    Result saveUserTrolley(String authorization,Integer userId, Integer productId);

    /**
     * 修改购物车中的商品
     * @param authorization 认证信息
     * @param userId 用户ID
     * @param productId 商品ID
     * @param productNumber 商品数量
     * @return
     */
    Result updateUserTrolley(String authorization, Integer userId, Integer productId,Integer productNumber);

    /**
     * 删除购物车中的商品
     * @param authorization 认证中心
     * @param userId 用户ID
     * @param productId 商品ID
     * @return 结果集
     */
    Result deleteUserTrolley(String authorization, Integer userId, Integer productId);
}
