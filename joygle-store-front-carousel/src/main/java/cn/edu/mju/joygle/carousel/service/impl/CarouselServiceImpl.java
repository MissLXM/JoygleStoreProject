package cn.edu.mju.joygle.carousel.service.impl;

import cn.edu.mju.joygle.common.client.product.ProductClient;
import cn.edu.mju.joygle.common.entity.dto.carousel.CarouselDto;
import cn.edu.mju.joygle.carousel.mapper.CarouselMapper;
import cn.edu.mju.joygle.carousel.service.CarouselService;
import cn.edu.mju.joygle.common.core.constants.HttpStatus;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.dto.product.ProductDto;
import cn.edu.mju.joygle.common.entity.pojo.StoreCarousel;
import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
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
 * ClassName: CarouselServiceImpl
 * Package: cn.edu.mju.joygle.carousel.service.impl
 * Description: 轮播图服务实现
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/28--12:44
 */
@Slf4j
@Service
public class CarouselServiceImpl implements CarouselService {

    @Setter(onMethod_ = @Autowired)
    private ProductClient productClient;

    @Setter(onMethod_ = @Autowired)
    private CarouselMapper carouselMapper;

    /**
     * 默认展示轮播图(6张,按照优先级)
     * @return 结果集
     */
    @Override
    public Result defaultShow() {
        // 初始化条件构造器
        QueryWrapper<StoreCarousel> wrapper = new QueryWrapper<>();
        // 设置条件(按照priority排序)
        wrapper.orderByDesc("priority");
        // 设置分页条件查询6条
        IPage<StoreCarousel> page = new Page<>(1,6);
        // 根据条件并只查询6条
        IPage<StoreCarousel> carouselIpage = carouselMapper.selectPage(page, wrapper);
        // 取出封装的满足条件的结果
        List<StoreCarousel> records = carouselIpage.getRecords();
        // 判断是否有值
        if (records.size() == 0) {
            return Result.fail().message("轮播图查询无").code(HttpStatus.NO_FOUND);
        }
        // 封装返回结果集
        List<CarouselDto> carouselDtos = new ArrayList<>();
        records.forEach(carousel ->{
            CarouselDto carouselDto = new CarouselDto();
            BeanUtil.copyProperties(carousel,carouselDto);
            carouselDtos.add(carouselDto);
        });
        return Result.ok(carouselDtos).message("轮播图查询成功");
    }

    /**
     * 通过轮播图ID跳转商品详细信息
     * @param categoryId 轮播图ID
     * @return 结果集
     */
    @Override
    public Result skipProductInfo(String authorization,Integer categoryId) {
        // 获取轮播图信息
        StoreCarousel carousel = carouselMapper.selectOne(
                new LambdaQueryWrapper<StoreCarousel>()
                        .eq(StoreCarousel::getCarouselId, categoryId));

        // 获取轮播图关联的商品信息
        Integer productId = carousel.getProductId();

        // 通过商品ID获取商品信息
        Object object = productClient.productInfoShow(authorization, productId).getData();

        // JSON转换
        String jsonString = JSON.toJSONString(object);
        ProductDto productDto = JSON.parseObject(jsonString, ProductDto.class);

        // 判空
        if (productDto == null) {
            return Result.fail().message("查无商品信息");
        }
        return Result.ok(productDto).message("商品查询成功");
    }
}
