package cn.edu.mju.joygle.user.controller;

import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.StoreUser;
import cn.edu.mju.joygle.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * ClassName: UserControllerApi
 * Package: cn.edu.mju.joygle.user.controller
 * Description: 用户控制类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/20--11:30
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name = "UserController",description = "用户服务")
public class UserController {

    @Setter(onMethod_ = @Autowired)
    private UserService service;

    @GetMapping("/getStoreUser")
    @Tag(name = "getStoreUser",description = "当前用户信息")
    public Result<StoreUser> getStoreUser(OAuth2Authentication oAuth2Authentication, Principal principal, Authentication authentication) {
        log.info("用户的角色有:{}",oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
        log.info("用户的名称为:{}",principal.getName());
        log.info("用户的角色有:{}",authentication.getAuthorities().toString());

        StoreUser user = service.usernameCheck(principal.getName(),null,null).getData();
        return Result.ok(user);
    }

    @PostMapping("/register")
    @Tag(name = "register", description = "注册用户")
    public Result registerUser(@RequestBody @Validated StoreUser user, BindingResult result) {
        // 1.参数是否有误
        if (result.hasErrors()) {
            return Result.fail().message("参数不合法");
        }
        // 2.判断数据库中是否有相同的手机号或者邮箱或者登录名称
        if (service.usernameCheck(
                user.getUsername(),
                user.getUserPhone()
                ,user.getUserEmail()).getData() != null) {
            return Result.fail().message("用户已存在");
        }
        // 3.密码加密
        String newPwd = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(newPwd);
        // 4.判断是否注册成功
        if( !service.save(user)) {
            return Result.fail().message("注册失败");
        }
        return Result.ok().message("注册成功");
    }

    @PutMapping("/updateUser")
    @Tag(name = "updateUser", description = "修改用户")
    public Result updateUser(@RequestBody @Validated StoreUser user, BindingResult result) {

        // 1.参数是否有误
        if (result.hasErrors()) {
            return Result.fail().message("参数不合法");
        }

        // 2.密码加密
        String newPwd = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(newPwd);

        // 3.判断是否修改成功
        if( !service.updateById(user)) {
            return Result.fail().message("修改失败");
        }

        return Result.ok().message("修改成功");
    }

}
