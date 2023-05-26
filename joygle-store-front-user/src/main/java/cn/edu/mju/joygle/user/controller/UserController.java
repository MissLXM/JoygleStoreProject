package cn.edu.mju.joygle.user.controller;

import cn.edu.mju.joygle.common.client.oauth.OauthClient;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.StoreUser;
import cn.edu.mju.joygle.common.param.LoginUserParam;
import cn.edu.mju.joygle.user.service.UserService;
import com.alibaba.fastjson.JSON;
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

    @Autowired
    private OauthClient oauthClient;

    @Setter(onMethod_ = @Autowired)
    private UserService service;

    @GetMapping("/getStoreUser")
    @Tag(name = "getStoreUser",description = "当前用户信息")
    public Result<StoreUser> getStoreUser(OAuth2Authentication oAuth2Authentication, Principal principal, Authentication authentication) {
        log.info("用户的角色有:{}",oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
        log.info("用户的名称为:{}",principal.getName());
        log.info("用户的角色有:{}",authentication.getAuthorities().toString());

        StoreUser user = service.usernameCheck(principal.getName()).getData();
        // 设置密码为空
        user.setPassword(null);
        return Result.ok(user);
    }

    @PostMapping("/login")
    @Tag(name = "login",description = "用户登录")
    public Result login(@RequestHeader("Authorization") String authorization,@RequestBody LoginUserParam loginUserParam) {
        log.info("UserController.login业务结束,用户信息:{}",JSON.toJSONString(loginUserParam));
         return oauthClient.login(authorization,loginUserParam);
    }

    @PostMapping("/register")
    @Tag(name = "register", description = "用户注册")
    public Result registerUser(@RequestBody @Validated StoreUser user, BindingResult result) {
        // 1.参数是否有误
        if (result.hasErrors()) {
            return Result.fail().message("参数不合法");
        }
        // 2.判断数据库中是否有相同的登录名称
        if (service.usernameAndOtherCheck(
                 user.getUsername()
                ,user.getUserEmail()
                ,user.getUserPhone()).size() != 0 ) {
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
    @Tag(name = "updateUser", description = "用户修改")
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

    @GetMapping("logout")
    @Tag(name = "logout", description = "用户登出")
    public Result logout(@RequestHeader("Authorization") String authorization) {
        return oauthClient.logout(authorization);
    }

}
