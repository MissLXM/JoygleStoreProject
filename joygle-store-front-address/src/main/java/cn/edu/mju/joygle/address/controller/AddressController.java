package cn.edu.mju.joygle.address.controller;

import cn.edu.mju.joygle.address.service.AddressService;
import cn.edu.mju.joygle.common.client.user.UserClient;
import cn.edu.mju.joygle.common.core.domain.Result;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: AddressController
 * Package: cn.edu.mju.joygle.address.controller
 * Description: 地址控制层
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/26--14:12
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Setter(onMethod_ = @Autowired)
    private UserClient userClient;

    @Setter(onMethod_ = @Autowired)
    private AddressService addressService;

    @GetMapping("/showUserAddress")
    public Result showUserAddress() {
        // 通过 Feign 获取用户ID
        Integer userId = userClient.getStoreUser().getData().getUserId();
        // 返回用户地址
        return addressService.showUserAddress(userId);
    }
}
