package cn.edu.mju.joygle.security.service.impl;

import cn.edu.mju.joygle.common.entity.pojo.StoreUser;
import cn.edu.mju.joygle.security.mapper.StoreUserMapper;
import cn.edu.mju.joygle.security.service.StoreUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: StoreUserServiceImpl
 * Package: cn.edu.mju.joygle.user.service.impl
 * Description:
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/20--10:50
 */
@Service
public class StoreUserServiceImpl extends ServiceImpl<StoreUserMapper, StoreUser> implements StoreUserService {

    @Setter(onMethod_ = @Autowired)
    private StoreUserMapper mapper;

    /**
     * 根据登录名称查询用户
     * @param username 登录名称
     * @return 用户
     */
    @Override
    public StoreUser getByUsername(String username) {
        // 初始化条件构造器
        LambdaQueryWrapper<StoreUser> wrapper = new LambdaQueryWrapper<>();
        // 设置条件
        wrapper.eq(StoreUser::getUsername,username);
        // 返回用户
        return mapper.selectOne(wrapper);
    }
}
