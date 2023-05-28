package cn.edu.mju.joygle.category.service.impl;

import cn.edu.mju.joygle.category.dto.CategoryDto;
import cn.edu.mju.joygle.category.mapper.CategoryMapper;
import cn.edu.mju.joygle.category.service.CategoryService;
import cn.edu.mju.joygle.common.core.constants.HttpStatus;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.StoreCategory;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: CategoryServiceImpl
 * Package: cn.edu.mju.joygle.category.service.impl
 * Description: 类别服务实现
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/28--12:29
 */
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Setter(onMethod_ = @Autowired)
    private CategoryMapper categoryMapper;


    /**
     * 一级类别展示(所有)
     * @return 结果集
     */
    @Override
    public Result oneLevelCategoryShow() {
        // 初始化条件构造器
        LambdaQueryWrapper<StoreCategory> wrapper = new LambdaQueryWrapper<>();
        // 设置条件
        wrapper
                .eq(StoreCategory::getCategoryParentId, 0);
        // 查询到一级类别的数据
        List<StoreCategory> categoryList = categoryMapper.selectList(wrapper);
        // 判断是否有数据
        if (categoryList.size() == 0) {
            return Result.fail().message("暂无一级类别").code(HttpStatus.NO_FOUND);
        }
        // 封装返回结果集
        List<CategoryDto> categoryDtos = new ArrayList<>();
        categoryList.forEach(category -> {
            CategoryDto categoryDto = new CategoryDto();
            BeanUtil.copyProperties(category, categoryDto);
            categoryDtos.add(categoryDto);
        });
        return Result.ok(categoryDtos).message("一级类别查询成功");
    }

    /**
     * 二级类别展示(根据一级类别)
     * @param categoryParentId 一级类别ID
     * @return 结果集
     */
    @Override
    public Result twoLevelCategoryShow(Integer categoryParentId) {
        // 初始化条件构造器
        LambdaQueryWrapper<StoreCategory> wrapper = new LambdaQueryWrapper<>();
        // 设置条件
        wrapper
                .eq(StoreCategory::getCategoryParentId, categoryParentId);
        // 查询到二级类别的数据
        List<StoreCategory> categoryList = categoryMapper.selectList(wrapper);
        // 判断是否有数据
        if (categoryList.size() == 0) {
            return Result.fail().message("暂无二级类别").code(HttpStatus.NO_FOUND);
        }
        // 封装返回结果集
        List<CategoryDto> categoryDtos = new ArrayList<>();
        categoryList.forEach(category -> {
            CategoryDto categoryDto = new CategoryDto();
            BeanUtil.copyProperties(category, categoryDto);
            categoryDtos.add(categoryDto);
        });
        return Result.ok(categoryDtos).message("二级类别查询成功");
    }
}
