package cn.edu.mju.joygle.collection.service;

import cn.edu.mju.joygle.common.core.domain.Result;

/**
 * ClassName: CollectionService
 * Package: cn.edu.mju.joygle.collection.service
 * Description: 收藏服务接口
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/28--12:20
 */
public interface CollectionService {

    /**
     * 展示用户收藏
     * @param userId 根据用户ID
     * @return 结果集
     */
    Result showUserCollection(String authorization,Integer userId,Integer currentPage,Integer pageSize);

    /**
     * 保存到用户收藏
     * @param userId 用户ID
     * @param productId 商品ID
     * @return 结果集
     */
    Result saveUserCollection(Integer userId,Integer productId);

    /**
     * 删除用户收藏
     * @param userId    用户ID
     * @param productId 商品ID
     * @return 结果集
     */
    Result deleteUserCollection(Integer userId, Integer productId);
}
