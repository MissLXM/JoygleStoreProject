package cn.edu.mju.joygle.collection.service.impl;

import cn.edu.mju.joygle.collection.mapper.CollectionMapper;
import cn.edu.mju.joygle.collection.service.CollectionService;
import cn.edu.mju.joygle.common.core.domain.Result;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: CollectionServiceImpl
 * Package: cn.edu.mju.joygle.collection.service.impl
 * Description: 收藏服务实现
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/28--12:21
 */
@Slf4j
@Service
public class CollectionServiceImpl implements CollectionService {

    @Setter(onMethod_ = @Autowired)
    private CollectionMapper collectionMapper;

    /**
     * 展示用户收藏
     * @param userId 根据用户ID
     * @return 结果集
     */
    @Override
    public Result showUserCollection(Integer userId) {
        return null;
    }

    /**
     * 保存到用户收藏
     * @param userId    用户ID
     * @param productId 商品ID
     * @return 结果集
     */
    @Override
    public Result saveUserCollection(Integer userId, Integer productId) {
        return null;
    }

    /**
     * 删除用户收藏
     * @param collectionId 收藏ID
     * @return 结果集
     */
    @Override
    public Result deleteUserCollection(Integer collectionId) {
        return null;
    }
}
