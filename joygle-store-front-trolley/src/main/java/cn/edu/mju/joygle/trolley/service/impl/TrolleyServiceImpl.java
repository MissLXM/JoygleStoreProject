package cn.edu.mju.joygle.trolley.service.impl;

import cn.edu.mju.joygle.common.client.product.ProductClient;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.dto.product.ProductDto;
import cn.edu.mju.joygle.common.entity.dto.trolley.TrolleyDto;
import cn.edu.mju.joygle.common.entity.pojo.StoreUserTrolley;
import cn.edu.mju.joygle.common.entity.param.product.ProductIdsParam;
import cn.edu.mju.joygle.trolley.mapper.TrolleyMapper;
import cn.edu.mju.joygle.trolley.service.TrolleyService;
import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: TrolleyServiceImpl
 * Package: cn.edu.mju.joygle.trolley.service.impl
 * Description: 购物车业务实现
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/29--0:58
 */
@Slf4j
@Service
public class TrolleyServiceImpl implements TrolleyService {

    @Setter(onMethod_ = @Autowired)
    private TrolleyMapper trolleyMapper;

    @Setter(onMethod_ = @Autowired)
    private ProductClient productClient;

    /**
     * 封装结果集
     *
     * @param userTrolleys 购物车商品ID
     * @return
     */
    private List<ProductDto> resultEncapsulate(String authorization,List<StoreUserTrolley> userTrolleys) {
        // 封装多个商品ID
        List<Integer> productIds = new ArrayList<>();
        userTrolleys.forEach( userTrolley -> productIds.add(userTrolley.getProductId()));

        // 获取商品信息
        List<ProductDto> productDtos = (List<ProductDto>) productClient.selectByProductIds(authorization, new ProductIdsParam(null, null, productIds)).getData();

        // JSON转换
        String jsonString = JSON.toJSONString(productDtos);
        productDtos = JSON.parseArray(jsonString, ProductDto.class);

        return productDtos;
    }

    /**
     * 展示用户购物车
     * @param authorization 认证信息
     * @param userId 用户ID
     * @return 结果集
     */
    @Override
    public Result showUserTrolley(String authorization, Integer userId) {

        // 查询
        List<StoreUserTrolley> userTrolleys = trolleyMapper
                .selectList(new LambdaQueryWrapper<StoreUserTrolley>().eq(StoreUserTrolley::getUserId, userId));

        // 封装结果集
        List<ProductDto> productDtos = resultEncapsulate(authorization,userTrolleys);
        List<TrolleyDto> trolleyDtos = new ArrayList<>();

        // 判空
        if (productDtos.size() == 0) {
            return Result.fail().message("购物车暂无数据");
        }

        // 数据拼接
        productDtos.forEach( productDto -> {
            // 获取商品的购物车信息
            StoreUserTrolley userTrolley = trolleyMapper
                    .selectOne(new LambdaQueryWrapper<StoreUserTrolley>()
                            .eq(StoreUserTrolley::getProductId,productDto.getProductId())
                            .eq(StoreUserTrolley::getUserId,userId));
            // 拷贝
            TrolleyDto trolleyDto = new TrolleyDto();
            BeanUtil.copyProperties(productDto, trolleyDto);
            trolleyDto.setProductNumber(userTrolley.getProductNumber());
            trolleyDtos.add(trolleyDto);
        });

        return Result.ok(trolleyDtos).message("购物车展示成功");
    }

    /**
     * 添加商品到购物车
     * @param authorization 认证信息
     * @param userId        用户ID
     * @param productId     商品ID
     * @return 结果集
     */
    @Override
    public Result saveUserTrolley(String authorization, Integer userId, Integer productId) {
        // 查询商品是否存在
        Object object = productClient.productInfoShow(authorization, productId).getData();

        // JSON转换
        String jsonString = JSON.toJSONString(object);
        ProductDto productDto = JSON.parseObject(jsonString, ProductDto.class);

        // 判空
        if (productDto == null) {
            return Result.fail().message("未查询到该商品");
        }

        // 是否还有库存
        if (productDto.getProductStock() == 0) {
            return Result.fail().message("商品库存不足");
        }

        // 判断是否第一次添加
        StoreUserTrolley userTrolley = trolleyMapper.selectOne(
                new LambdaQueryWrapper<StoreUserTrolley>()
                        .eq(StoreUserTrolley::getUserId, userId)
                        .eq(StoreUserTrolley::getProductId, productId));

        if (userTrolley != null) {
            // 商品数量 + 1
            userTrolley.setProductNumber(userTrolley.getProductNumber() + 1);
            // 是否添加成功
            int rows = trolleyMapper.updateById(userTrolley);
            if (rows == 0) {
                return Result.fail().message("加入购物车失败");
            }
            return Result.ok().message("加入购物车成功");
        }

        // 当userTrolley为空时,添加商品到购物车
        userTrolley = new StoreUserTrolley()
                .setUserId(userId)
                .setProductId(productId)
                .setProductNumber(1);
        int rows = trolleyMapper.insert(userTrolley);
        if (rows == 0) {
            return Result.fail().message("加入购物车失败");
        }
        return Result.ok().message("加入购物车成功");
    }

    /**
     * 修改购物车中的商品
     * @param authorization 认证信息
     * @param userId 用户ID
     * @param productId 商品ID
     * @param productNumber 商品数量
     * @return
     */
    @Override
    public Result updateUserTrolley(String authorization, Integer userId, Integer productId,Integer productNumber) {
        // 查询商品是否存在
        Object object =  productClient.productInfoShow(authorization, productId).getData();

        // JSON转换
        String jsonString = JSON.toJSONString(object);
        ProductDto productDto = JSON.parseObject(jsonString, ProductDto.class);

        // 判空
        if (productDto == null) {
            return Result.fail().message("未查询到该商品");
        }

        // 是否还有库存
        if (productDto.getProductStock() < productNumber) {
            return Result.fail().message("商品库存不足");
        }

        // 判断是否第一次添加
        StoreUserTrolley userTrolley = trolleyMapper.selectOne(
                new LambdaQueryWrapper<StoreUserTrolley>()
                        .eq(StoreUserTrolley::getUserId, userId)
                        .eq(StoreUserTrolley::getProductId, productId));

        if (userTrolley != null) {
            // 商品数量为输入的商品数量
            userTrolley.setProductNumber(productNumber);
            // 是否添加成功
            int rows = trolleyMapper.updateById(userTrolley);
            if (rows == 0) {
                return Result.fail().message("修改购物车商品数量失败");
            }
            return Result.ok().message("修改购物车商品数量成功");
        }

        // 当 userTrolley 为空时,添加商品到购物车
        userTrolley = new StoreUserTrolley()
                .setUserId(userId)
                .setProductId(productId)
                .setProductNumber(productNumber);
        int rows = trolleyMapper.insert(userTrolley);
        if (rows == 0) {
            return Result.fail().message("加入购物车失败");
        }
        return Result.ok().message("加入购物车成功");
    }

    /**
     * 删除购物车中的商品
     * @param authorization 认证中心
     * @param userId        用户ID
     * @param productId     商品ID
     * @return 结果集
     */
    @Override
    public Result deleteUserTrolley(String authorization, Integer userId, Integer productId) {
        // 删除数据
        int rows = trolleyMapper.delete(
                new LambdaQueryWrapper<StoreUserTrolley>()
                        .eq(StoreUserTrolley::getUserId, userId)
                        .eq(StoreUserTrolley::getProductId, productId));

        if (rows == 0) {
            return Result.fail().message("删除购物车商品失败");
        }
        return Result.ok().message("删除购物车商品成功");
    }
}
