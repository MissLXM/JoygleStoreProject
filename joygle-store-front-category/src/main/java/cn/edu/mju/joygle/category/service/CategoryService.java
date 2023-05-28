package cn.edu.mju.joygle.category.service;

import cn.edu.mju.joygle.common.core.domain.Result;

/**
 * ClassName: CategoryService
 * Package: cn.edu.mju.joygle.category.service
 * Description: 类别服务接口
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/28--12:29
 */
public interface CategoryService {

    /**
     * 一级类别展示(所有)
     * @return 结果集
     */
    Result oneLevelCategoryShow();

    /**
     * 二级类别展示(根据一级类别)
     * @param categoryParentId 一级类别ID
     * @return 结果集
     */
    Result twoLevelCategoryShow(Integer categoryParentId);
}
