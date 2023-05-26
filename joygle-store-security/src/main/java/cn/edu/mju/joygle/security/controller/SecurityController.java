package cn.edu.mju.joygle.security.controller;

import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.param.LoginUserParam;
import cn.edu.mju.joygle.security.dto.LoginUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: SecurityController
 * Package: cn.edu.mju.joygle.security.controller
 * Description: 安全框架用来测试的接口
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/24--15:12
 */
@Slf4j
@RestController
@RequestMapping("/oauth")
public class SecurityController {

        @Autowired
        private TokenStore tokenStore;

        @Autowired
        private TokenEndpoint tokenEndpoint;

         @PostMapping( "/login")
         public Result login(@RequestBody LoginUserParam loginUserParam) {
            log.info("SecurityController.login业务结束,结果:{}","进入到自定义接口");
            // 创建客户端信息
            User clientUser = new User("client", "123456", new ArrayList<>());
            // 生成认证的客户端
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(clientUser, null, new ArrayList<>());
            // 封装成一个UserPassword方式的参数体
            Map<String, String> map = new HashMap<>();
            map.put("username", loginUserParam.getUsername());
            map.put("password", loginUserParam.getPassword());
            //授权模式为：密码模式
            map.put("grant_type", "password");

            // 调用自带的获取token方法。
            OAuth2AccessToken resultToken = null;
            try {
                resultToken = tokenEndpoint.postAccessToken(token, map).getBody();
            } catch (HttpRequestMethodNotSupportedException e) {
                return Result.fail().message("token = null");
            }
            // 登录回显信息
            LoginUserDto loginUserDto = new LoginUserDto();
            if (resultToken != null) {
                loginUserDto
                        .setAccessToken(resultToken.getValue())
                        .setRefreshToken(resultToken.getRefreshToken().getValue())
                        .setExpiresIn(resultToken.getExpiresIn())
                        .setScope(resultToken.getScope())
                        .setJti((String) resultToken.getAdditionalInformation().get("jti"));
            }
            // 返回
            return Result.ok(loginUserDto);
        }

        @GetMapping("/logout")
        public Result logout(@RequestHeader("Authorization") String authorization) {
            // 切割令牌(bearer )
            String token = authorization.split(" ")[1];
            log.info("令牌 = " + token);

            // 读取令牌
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);

            if (oAuth2AccessToken != null) {
                // 移除令牌和移除刷新令牌
                tokenStore.removeAccessToken(oAuth2AccessToken);
                return Result.ok().message("登出成功");

            } else {
                return Result.fail().message("token已失效,请勿重复登出");
            }
        }
}
