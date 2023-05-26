package cn.edu.mju.joygle.security.service;


import cn.edu.mju.joygle.common.entity.StoreUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * ClassName: StoreUserService
 * Package: cn.edu.mju.joygle.user.service
 * Description:
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/20--10:47
 */
public interface StoreUserService extends IService<StoreUser> {

    /**
     * 根据登录名称查询用户
     * @param username 登录名称
     * @return 用户
     */
    StoreUser getByUsername(String username);

}
