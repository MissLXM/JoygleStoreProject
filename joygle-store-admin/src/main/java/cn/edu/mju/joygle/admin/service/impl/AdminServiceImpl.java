package cn.edu.mju.joygle.admin.service.impl;

import cn.edu.mju.joygle.admin.redis.Cache.RedisCache;
import cn.edu.mju.joygle.admin.security.domain.MyUserDetails;
import cn.edu.mju.joygle.admin.service.AdminService;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.param.admin.LoginParam;
import cn.edu.mju.joygle.common.entity.pojo.StoreAdmin;
import cn.edu.mju.joygle.common.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * ClassName: AdminServiceImpl
 * Package: cn.edu.mju.joygle.admin.service.impl
 * Description: 管理员业务实现
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--15:34
 */
@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 登录认证
     * @param loginParam 登录参数
     * @return 结果集
     */
    @Override
    public Result login(LoginParam loginParam) {
        // 用户名密码封装
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginParam.getUsername(),
                        loginParam.getPassword()
                );
        // 认证信息
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 认证是否成功
        if (Objects.isNull(authenticate)) {
            return Result.fail().message("用户名或密码错误");
        }

        // token生成
        MyUserDetails userDetails = (MyUserDetails) authenticate.getPrincipal();
        String adminName = userDetails.getAdmin().getAdminName();
        String token = JwtUtil.createJwt(adminName);

        // token存入redis
        redisCache.setCacheObject("login:" + adminName,userDetails);

        // 返回token
        return Result.ok(token).message("登录成功");
    }

    /**
     * 登出
     * @return 结果集
     */
    @Override
    public Result logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        String adminName = userDetails.getAdmin().getAdminName();
        redisCache.deleteObject("login:" + adminName);
        return Result.ok().message("登出成功");
    }

}
