package cn.edu.mju.joygle.security.service.impl;


import cn.edu.mju.joygle.common.entity.StoreUser;
import cn.edu.mju.joygle.security.service.StoreUserService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: UserDetailServiceImpl
 * Package: cn.edu.mju.joygle.security.service
 * Description:
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/23--13:21
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Setter(onMethod_ = @Autowired)
    private StoreUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        StoreUser user = userService.getByUsername(username);
        // 设置权限
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (user != null) {
            //获取用户信息
            String role = user.getUserRole();
            //声明授权文件
            if (role != null) {
                //spring Security中权限名称必须满足该角色
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
                grantedAuthorities.add(grantedAuthority);
            }
            // 返回该用户信息
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        }
        log.info("UserDetailServiceImpl.loadUserByUsername业务结束,结果:{}","user用户为空");
        return null;
    }
}