package cn.edu.mju.joygle.admin.service.impl;

import cn.edu.mju.joygle.admin.mapper.CategoryMapper;
import cn.edu.mju.joygle.admin.service.CategoryService;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.pojo.StoreCategory;
import cn.edu.mju.joygle.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
        if (!StringUtils.isNoneBlank(keyword)) {
            wrapper.like(StoreCategory::getCategoryName, keyword);
        }

        // 查询
        IPage<StoreCategory> categoryIPage = categoryMapper.selectPage(ipage, wrapper);
        if (categoryIPage.getRecords().size() == 0) {
            return Result.fail().message("查询失败");
        }
        return Result.ok(categoryIPage).message("查询成功");
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
     *
     * @param categoryId 类别ID
     * @return 结果集
     */
    @Override
    public Result categoryInfoDelete(Integer categoryId) {
        // 根据ID删除
        int rows = categoryMapper.deleteById(categoryId);
        // 判空返回
        return getResult(rows);
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
