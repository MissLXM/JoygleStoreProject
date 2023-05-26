package cn.edu.mju.joygle.user.service.impl;

import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.StoreUser;
import cn.edu.mju.joygle.user.mapper.UserMapper;
import cn.edu.mju.joygle.user.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private UserMapper mapper;

    /**
     * 根据登录名称查询用户
     * @param username 登录名称
     * @return 用户
     */
    @Override
    public Result<StoreUser> usernameCheck(String username,String phone,String email){
        // 初始化条件构造器
        LambdaQueryWrapper<StoreUser> wrapper = new LambdaQueryWrapper<>();
        // 设置条件
        wrapper.and(user ->
                user.eq(StoreUser::getUsername, username)
                        .or().eq(StoreUser::getUserPhone, phone)
                        .or().eq(StoreUser::getUserEmail, email)
        );
        // 返回用户
        return Result.ok(mapper.selectOne(wrapper));
    }

}
