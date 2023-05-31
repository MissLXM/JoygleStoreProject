package cn.edu.mju.joygle.collection.service.impl;

import cn.edu.mju.joygle.collection.mapper.CollectionMapper;
import cn.edu.mju.joygle.collection.service.CollectionService;
import cn.edu.mju.joygle.common.client.product.ProductClient;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.dto.product.ProductDto;
import cn.edu.mju.joygle.common.entity.pojo.StoreUserCollection;
import cn.edu.mju.joygle.common.entity.param.product.ProductIdsParam;
import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    private ProductClient productClient;

    @Setter(onMethod_ = @Autowired)
    private CollectionMapper collectionMapper;

    /**
     * 展示用户收藏
     * @param userId 根据用户ID
     * @return 结果集
     */
    @Override
    public Result showUserCollection(String authorization,Integer userId,Integer currentPage,Integer pageSize) {
        // 初始化数据
        LambdaQueryWrapper<StoreUserCollection> wrapper = new LambdaQueryWrapper<>();
        IPage<StoreUserCollection> page = new Page<>(currentPage, pageSize);

        // 设置条件
        wrapper.eq(StoreUserCollection::getUserId,userId);

        // 查询
        IPage<StoreUserCollection> oldUserCollectionIPage = collectionMapper.selectPage(page, wrapper);
        log.info("oldUserCollectionIPage的总当前页" +  oldUserCollectionIPage.getCurrent());
        log.info("oldUserCollectionIPage的每页显示" +  oldUserCollectionIPage.getSize());
        log.info("oldUserCollectionIPage的总记录数" +  oldUserCollectionIPage.getPages());
        // 初始化参数
        List<Integer> productIds = new ArrayList<>();

        // 获取所有的商品ID值
        oldUserCollectionIPage.getRecords().forEach( collection -> productIds.add(collection.getProductId()));

        // 根据商品ID查询商品信息
        ProductIdsParam productIdsParam = new ProductIdsParam(currentPage, pageSize, productIds);
        List<ProductDto> productDtos = (List<ProductDto>) productClient.selectByProductIds(authorization, productIdsParam).getData();

        // JSON格式转换
        String jsonString = JSON.toJSONString(productDtos);
        productDtos = JSON.parseArray(jsonString, ProductDto.class);

        // 封装返回结果集
        IPage<ProductDto> newUserCollectionIPage =  new Page<>(currentPage, pageSize);
        BeanUtil.copyProperties(oldUserCollectionIPage, newUserCollectionIPage);

        // 返回结果集
        newUserCollectionIPage.setRecords(productDtos);
        newUserCollectionIPage.setTotal(oldUserCollectionIPage.getRecords().size());
        return Result.ok(newUserCollectionIPage).message("查询成功");
    }

    /**
     * 保存到用户收藏
     * @param userId    用户ID
     * @param productId 商品ID
     * @return 结果集
     */
    @Override
    public Result saveUserCollection(Integer userId, Integer productId) {
        // 初始化数据
        LambdaQueryWrapper<StoreUserCollection> wrapper = new LambdaQueryWrapper<>();

        // 设置条件
        wrapper
                .eq(StoreUserCollection::getUserId, userId)
                .eq(StoreUserCollection::getProductId, productId);

        // 先查询是否有该条数据
        List<StoreUserCollection> userCollections = collectionMapper.selectList(wrapper);
        if (userCollections.size() != 0) {
            return Result.fail().message("已收藏,无需重复收藏");
        }

        // 插入数据
        StoreUserCollection userCollection = new StoreUserCollection()
                .setUserId(userId)
                .setProductId(productId);
        int rows = collectionMapper.insert(userCollection);

        // 判空
        if (rows == 0) {
            return Result.fail().message("收藏失败");
        }
        return Result.ok().message("收藏成功");
    }

    /**
     * 删除用户收藏
     * @param userId    用户ID
     * @param productId 商品ID
     * @return 结果集
     */
    @Override
    public Result deleteUserCollection(Integer userId, Integer productId) {
        // 初始化数据
        LambdaQueryWrapper<StoreUserCollection> wrapper = new LambdaQueryWrapper<>();

        // 设置条件
        wrapper
                .eq(StoreUserCollection::getUserId, userId)
                .eq(StoreUserCollection::getProductId, productId);

        // 删除
        int rows = collectionMapper.delete(wrapper);

        // 判空
        if (rows == 0) {
            return Result.fail().message("删除收藏失败");
        }
        return Result.ok().message("删除收藏成功");
    }
}
