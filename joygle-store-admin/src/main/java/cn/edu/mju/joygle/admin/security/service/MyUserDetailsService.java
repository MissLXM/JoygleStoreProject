package cn.edu.mju.joygle.admin.security.service;

import cn.edu.mju.joygle.admin.mapper.AdminMapper;
import cn.edu.mju.joygle.admin.security.domain.MyUserDetails;
import cn.edu.mju.joygle.common.entity.pojo.StoreAdmin;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * ClassName: MyUserDetailsService
 * Package: cn.edu.mju.joygle.admin.security.service
 * Description: 用户登录自定义
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--12:58
 */
@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Setter(onMethod_ = @Autowired)
    private AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名密码查询数据库
        StoreAdmin admin = adminMapper.selectOne(
                new LambdaQueryWrapper<StoreAdmin>()
                        .eq(StoreAdmin::getUsername, username));

        // 判空
        if (admin == null) {
            throw new RuntimeException("用户名或密码错误");
        }

        MyUserDetails userDetails = new MyUserDetails().setAdmin(admin);
        // 存储到上下文中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return userDetails;
    }
}
