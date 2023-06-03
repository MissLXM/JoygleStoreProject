package cn.edu.mju.joygle.admin.service;

import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.pojo.StoreCarousel;

/**
 * ClassName: CarouselService
 * Package: cn.edu.mju.joygle.admin.service
 * Description: 轮播图业务接口
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--19:14
 */
public interface CarouselService {

    /**
     * 轮播图展示
     * @param currentPage 当前页码
     * @param pageSize 每页显示数
     * @return 结果集
     */
    Result carouselShow(Integer currentPage, Integer pageSize);

    /**
     * 轮播图保存
     * @param carousel 轮播图信息
     * @return 结果集
     */
    Result carouselSave(StoreCarousel carousel);

    /**
     * 轮播图修改
     * @param carousel 轮播图信息
     * @return 结果集
     */
    Result carouselUpdate(StoreCarousel carousel);

    /**
     * 轮播图删除
     * @param carouselId 轮播图ID
     * @return 结果集
     */
    Result carouselDelete(Integer carouselId);
}
