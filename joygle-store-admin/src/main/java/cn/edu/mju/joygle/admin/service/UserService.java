package cn.edu.mju.joygle.admin.service;

import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.pojo.StoreUser;

/**
 * ClassName: UserService
 * Package: cn.edu.mju.joygle.admin.service
 * Description: 用户业务接口
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--19:18
 */
public interface UserService {

    /**
     * 用户信息展示
     * @param currentPage 当前页码
     * @param pageSize 每页显示数
     * @param keyword 搜索关键字
     * @return 结果集
     */
    Result userInfoShow(Integer currentPage, Integer pageSize,String keyword);

    /**
     * 保存用户信息
     * @param user 用户信息
     * @return 结果集
     */
    Result userInfoSave(StoreUser user);

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return 结果集
     */
    Result userInfoUpdate(StoreUser user);

    /**
     * 删除用户信息
     * @param userId 用户ID
     * @return 结果集
     */
    Result userInfoDelete(Integer userId);
}
