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
}
