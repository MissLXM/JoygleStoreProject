package cn.edu.mju.joygle.admin.service;

import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.param.admin.LoginParam;

/**
 * ClassName: AdminService
 * Package: cn.edu.mju.joygle.admin.service
 * Description: 管理员业务接口
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--15:34
 */

public interface AdminService {

    /**
     * 登录认证
     * @param loginParam 登录参数
     * @return 结果集
     */
    Result login(LoginParam loginParam);

    /**
     * 登出
     * @return 结果集
     */
    Result logout();
}
