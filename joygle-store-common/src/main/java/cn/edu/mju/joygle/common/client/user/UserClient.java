package cn.edu.mju.joygle.common.client.user;

import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.StoreUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * ClassName: UserClient
 * Package: cn.edu.mju.joygle.common.client
 * Description: 用户服务客户端
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/23--13:22
 */
@FeignClient("user-service")
public interface UserClient {

    /**
     * 当前用户信息
     * @return 用户
     */
    @GetMapping("/user/getStoreUser")
    Result<StoreUser> getStoreUser(@RequestHeader("Authorization") String authorization);
}
