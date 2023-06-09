package cn.edu.mju.joygle.common.client.oauth;

import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.param.oauth.LoginUserParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * ClassName: OauthClient
 * Package: cn.edu.mju.joygle.common.client.oauth
 * Description: 认证客户端
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/26--23:05
 */
@FeignClient(value = "security-service")
public interface OauthClient {

    /**
     * 登录接口
     * @param authorization 认证信息
     * @param loginUserParam 登录参数
     * @return 结果集
     */
    @PostMapping("/oauth/login")
    Result login(@RequestHeader("Authorization") String authorization,@RequestBody LoginUserParam loginUserParam);

    /**
     * 登出接口
     * @param authorization 认证信息
     * @return 结果集
     */
    @GetMapping("/oauth/logout")
    Result logout(@RequestHeader("Authorization") String authorization);
}
