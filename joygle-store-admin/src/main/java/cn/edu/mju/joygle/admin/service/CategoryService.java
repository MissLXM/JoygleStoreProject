package cn.edu.mju.joygle.admin.service;

import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.pojo.StoreCategory;

import java.util.List;

/**
 * ClassName: CategoryService
 * Package: cn.edu.mju.joygle.admin.service
 * Description: 类别业务接口
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--19:16
 */
public interface CategoryService {

    /**
     * 类别展示
     * @param currentPage 当前页码
     * @param pageSize 每页显示数
     * @param keyword 搜索关键字
     * @return 结果集
     */
    Result categoryInfoShow(Integer currentPage,Integer pageSize,String keyword);

    /**
     * 类别保存
     * @param category 类别信息
     * @return 结果集
     */
    Result categoryInfoSave(StoreCategory category);

    /**
     * 类别修改
     * @param category 类别信息
     * @return 结果集
     */
    Result categoryInfoUpdate(StoreCategory category);

    /**
     * 类别删除
     * @param categoryId 类别ID
     * @return 结果集
     */
    Result categoryInfoDelete(Integer categoryId);

    /**
     * 多类别删除
     * @param categoryIds 多类别ID
     * @return 结果集
     */
    Result deleteByCategoryIds(List<Integer> categoryIds);
}
