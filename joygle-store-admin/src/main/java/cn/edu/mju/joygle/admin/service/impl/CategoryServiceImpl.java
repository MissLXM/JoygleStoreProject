package cn.edu.mju.joygle.admin.service.impl;

import cn.edu.mju.joygle.admin.dto.CategoryDto;
import cn.edu.mju.joygle.admin.dto.CategoryDtos;
import cn.edu.mju.joygle.admin.mapper.CategoryMapper;
import cn.edu.mju.joygle.admin.service.CategoryService;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.pojo.StoreCategory;
import cn.edu.mju.joygle.common.utils.StringUtils;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * ClassName: CategoryServiceImpl
 * Package: cn.edu.mju.joygle.admin.service.impl
 * Description: 类别业务接口
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--19:16
 */
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Setter(onMethod_ = @Autowired)
    private CategoryMapper categoryMapper;


    /**
     * 类别展示
     * @param currentPage 当前页码
     * @param pageSize    每页显示数
     * @param keyword     搜索关键字
     * @return 结果集
     */
    @Override
    public Result categoryInfoShow(Integer currentPage, Integer pageSize, String keyword) {
        // 初始化数据
        IPage<StoreCategory> ipage = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<StoreCategory> wrapper = new LambdaQueryWrapper<>();

        // 判断是否有搜索
        if("null".equals(keyword)){  keyword = null;}
        if (StringUtils.isNotEmpty(keyword)) {
            wrapper.like(StoreCategory::getCategoryName, keyword);
        }

        // 查询
        IPage<StoreCategory> categoryIPage = categoryMapper.selectPage(ipage, wrapper);
        if (categoryIPage.getRecords().size() == 0) {
            return Result.fail().message("查询失败");
        }

        // 深拷贝
        IPage<CategoryDtos> categoryDtosIPage = new Page<>(currentPage, pageSize);
        BeanUtil.copyProperties(categoryIPage, categoryDtosIPage);

        // 先查询一级类别
        List<StoreCategory> categoryList = categoryMapper.selectList(
                new LambdaQueryWrapper<StoreCategory>()
                        .eq(StoreCategory::getCategoryParentId, 0)
        );

        // 准备参数
        List<CategoryDtos> categoryDtoList = new ArrayList<>();

        // 按一级类别的ID分类
        categoryList.forEach(category -> {
            // 类别分类
            List<StoreCategory> categories = categoryMapper.selectList(
                    new LambdaQueryWrapper<StoreCategory>()
                            .eq(StoreCategory::getCategoryParentId,category.getCategoryId())
            );
            // 添加到返回的结果集中
            CategoryDtos categoryDtos = new CategoryDtos();
            List<CategoryDto> categorySuns = new ArrayList<>();
            categories.forEach(dto -> {
                // 封装二级类别
                CategoryDto categoryDto = new CategoryDto();
                BeanUtil.copyProperties(dto, categoryDto);
                categorySuns.add(categoryDto);
            });
            // 封装一级类别
            BeanUtil.copyProperties(category, categoryDtos);
            categoryDtos.setCategorySuns(categorySuns);
            categoryDtoList.add(categoryDtos);
        });
        categoryDtosIPage.setRecords(categoryDtoList);
        return Result.ok(categoryDtosIPage).message("查询成功");
    }

    /**
     * 类别保存
     *
     * @param category 类别信息
     * @return 结果集
     */
    @Override
    public Result categoryInfoSave(StoreCategory category) {
        // 插入
        int rows = categoryMapper.insert(category);
        // 判空返回
        return getResult(rows);
    }

    /**
     * 类别修改
     *
     * @param category 类别信息
     * @return 结果集
     */
    @Override
    public Result categoryInfoUpdate(StoreCategory category) {
        // 修改
        int rows = categoryMapper.updateById(category);
        // 判空返回
        return getResult(rows);
    }

    /**
     * 类别删除
     * @param categoryId 类别ID
     * @return 结果集
     */
    @Override
    public Result categoryInfoDelete(Integer categoryId) {
        // 判断这个类别是否有二级类别
        List<StoreCategory> categoryList = categoryMapper.selectList(
                new LambdaQueryWrapper<StoreCategory>()
                        .eq(StoreCategory::getCategoryParentId, categoryId)
        );
        // 判断是否为空
        if (categoryList.size() != 0) {
            return Result.fail().message("类别ID为:" + categoryId + "的类别有子类别,删除失败");
        }
        // 根据ID删除
        int rows = categoryMapper.deleteById(categoryId);
        // 判空返回
        return getResult(rows);
    }

    @Override
    public Result deleteByCategoryIds(List<Integer> categoryIds) {
        // 遍历删除
        for (Integer categoryId : categoryIds) {
            // 判断这个类别是否有二级类别
            List<StoreCategory> categoryList = categoryMapper.selectList(
                    new LambdaQueryWrapper<StoreCategory>()
                            .eq(StoreCategory::getCategoryParentId, categoryId)
            );
            // 判断是否为空
            if (categoryList.size() != 0) {
                return Result.fail().message("类别ID为:" + categoryId + "的类别有子类别,删除失败");
            }

            int rows = categoryMapper.deleteById(categoryId);

            if (rows == 0) {
                return Result.fail().message("类别ID为:" + categoryId + "的类别删除失败");
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
