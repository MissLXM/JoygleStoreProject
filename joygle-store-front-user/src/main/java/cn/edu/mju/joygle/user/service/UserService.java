package cn.edu.mju.joygle.user.service;


import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.pojo.StoreUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ClassName: UserService
 * Package: cn.edu.mju.joygle.user.service
 * Description:
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/20--10:47
 */
public interface UserService extends IService<StoreUser> {

    /**
     * 检查登录名称
     * @param username 登录名称
     * @return 用户
     */
    Result<StoreUser> usernameCheck(String username);

    /**
     * 检查登录名称、Email、手机号是否存在
     * @param username 登录名称
     * @param email Email
     * @param phone 手机号
     * @return
     */
    List<StoreUser> usernameAndOtherCheck(String username,String email,String phone);

    /**
     * 更新头像
     * @param userId 用户ID
     * @param avatarFile 头像文件
     * @return 结果集
     */
    Result updateUserAvatar(Integer userId, MultipartFile avatarFile);
}
