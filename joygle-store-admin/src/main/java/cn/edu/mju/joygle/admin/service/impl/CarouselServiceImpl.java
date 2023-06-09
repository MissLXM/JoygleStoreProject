package cn.edu.mju.joygle.admin.service.impl;

import cn.edu.mju.joygle.admin.mapper.CarouselMapper;
import cn.edu.mju.joygle.admin.service.CarouselService;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.pojo.StoreCarousel;
import cn.edu.mju.joygle.common.entity.pojo.StoreProduct;
import cn.edu.mju.joygle.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: CarouselServiceImpl
 * Package: cn.edu.mju.joygle.admin.service.impl
 * Description: 轮播图业务实现
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--19:15
 */
@Slf4j
@Service
public class CarouselServiceImpl implements CarouselService {

    @Setter(onMethod_ = @Autowired)
    private CarouselMapper carouselMapper;


    /**
     * 轮播图展示
     * @param currentPage 当前页码
     * @param pageSize    每页显示数
     * @return 结果集
     */
    @Override
    public Result carouselShow(Integer currentPage, Integer pageSize ) {
        // 初始化数据
        IPage<StoreCarousel> ipage = new Page<>(currentPage, pageSize);

        // 查询
        IPage<StoreCarousel> carouselIPage = carouselMapper.selectPage(ipage, null);
        if (carouselIPage.getRecords().size() == 0) {
            return Result.fail().message("查询失败");
        }
        return Result.ok(ipage).message("查询成功");
    }

    /**
     * 轮播图保存
     * @param carousel 轮播图信息
     * @return 结果集
     */
    @Override
    public Result carouselSave(StoreCarousel carousel) {
        // 保存
        int rows = carouselMapper.insert(carousel);
        // 判空返回
        return getResult(rows);
    }

    /**
     * 轮播图修改
     * @param carousel 轮播图信息
     * @return 结果集
     */
    @Override
    public Result carouselUpdate(StoreCarousel carousel) {
        // 修改
        int rows = carouselMapper.updateById(carousel);
        // 判空返回
        return getResult(rows);
    }

    /**
     * 轮播图删除
     * @param carouselId 轮播图ID
     * @return 结果集
     */
    @Override
    public Result carouselDelete(Integer carouselId) {
        // 根据轮播图ID删除
        int rows = carouselMapper.deleteById(carouselId);
        // 判空返回
        return getResult(rows);
    }

    @Override
    public Result deleteCarouselIds(List<Integer> carouselIds) {
        // 遍历删除
        for (Integer carouselId : carouselIds) {
            int rows = carouselMapper.deleteById(carouselId);
            if (rows == 0) {
                return Result.fail().message("轮播图ID为:" + carouselId +"的轮播图删除失败");
            }
        }
        return Result.ok().message("删除成功");
    }

    /**
     * 判空返回
     * @param rows 影响行数
     * @return 结果集
     */
    private static Result getResult(int rows) {
        // 是否保存成功
        if (rows == 0) {
            return Result.fail().message("失败");
        }
        return Result.ok().message("成功");
    }
}
