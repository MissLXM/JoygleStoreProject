package cn.edu.mju.joygle.carousel.service;

import cn.edu.mju.joygle.common.core.domain.Result;

/**
 * ClassName: CarouselService
 * Package: cn.edu.mju.joygle.carousel.service
 * Description: 轮播图服务接口
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/28--12:43
 */
public interface CarouselService {

    /**
     * 默认展示轮播图(6张,按照优先级)
     * @return 结果集
     */
    Result defaultShow();

    /**
     * 通过轮播图ID跳转商品详细信息
     * @param authorization 认证信息
     * @param categoryId 轮播图ID
     * @return 结果集
     */
    Result skipProductInfo(String authorization, Integer categoryId);
}
