package cn.edu.mju.joygle.user.service.impl;

import cn.edu.mju.joygle.common.client.statics.StaticClient;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.pojo.StoreUser;
import cn.edu.mju.joygle.user.mapper.UserMapper;
import cn.edu.mju.joygle.user.service.UserService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ClassName: UserServiceImpl
 * Package: cn.edu.mju.joygle.user.service.impl
 * Description:
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/20--10:50
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, StoreUser> implements UserService {

    @Setter(onMethod_ = @Autowired)
    private StaticClient staticClient;

    @Setter(onMethod_ = @Autowired)
    private UserMapper mapper;

    /**
     * 根据登录名称查询用户
     * @param username 登录名称
     * @return 用户
     */
    @Override
    public Result<StoreUser> usernameCheck(String username){
        // 初始化条件构造器
        LambdaQueryWrapper<StoreUser> wrapper = new LambdaQueryWrapper<>();
        // 设置条件
        wrapper.eq(StoreUser::getUsername,username);
        // 返回用户
        return Result.ok(mapper.selectOne(wrapper));
    }

    /**
     * 检查登录名称、Email、手机号是否存在
     * @param username 登录名称
     * @param email Email
     * @param phone 手机号
     * @return
     */
    @Override
    public List<StoreUser> usernameAndOtherCheck(String username, String email, String phone) {
        // 初始化条件构造器
        LambdaQueryWrapper<StoreUser> wrapper = new LambdaQueryWrapper<>();
        // 设置条件
        wrapper.eq(StoreUser::getUsername,username)
                .or().eq(StoreUser::getUserEmail,email)
                .or().eq(StoreUser::getUserPhone,phone);
        // 返回用户
        return mapper.selectList(wrapper);
    }

    /**
     * 更新头像
     * @param userId     用户ID
     * @param avatarFile 头像文件
     * @return 结果集
     */
    @Override
    public Result updateUserAvatar(Integer userId, MultipartFile avatarFile) {

        // 将图片下载到本地资源模块中
        Object object = staticClient.downloadAvatar(avatarFile).getData();

        // JSON转换
        String jsonString = JSON.toJSONString(object);
        String avatarPath = JSON.parseObject(jsonString, String.class);

        // 判空
        if (avatarPath == null) {
            return Result.fail().message("图片保存失败");
        }

        // 根据用户ID查询用户信息
        StoreUser storeUser = mapper.selectOne(new LambdaQueryWrapper<StoreUser>().eq(StoreUser::getUserId, userId));

        // 设置用户头像地址
        storeUser.setUserAvatar(avatarPath);

        // 修改用户信息
        int rows = mapper.updateById(storeUser);

        // 判空
        if (rows == 0) {
            return Result.fail().message("头像修改失败");
        }
        return Result.ok().message("头像修改成功");
    }

}
