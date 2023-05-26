package cn.edu.mju.joygle.address.controller;

import cn.edu.mju.joygle.address.service.AddressService;
import cn.edu.mju.joygle.common.client.user.UserClient;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.StoreUserAddress;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * ClassName: AddressController
 * Package: cn.edu.mju.joygle.address.controller
 * Description: 地址控制层
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/26--14:12
 */
@Slf4j
@RestController
@RequestMapping("/address")
public class AddressController {

    @Setter(onMethod_ = @Autowired)
    private UserClient userClient;

    @Setter(onMethod_ = @Autowired)
    private AddressService addressService;

    @GetMapping("/showUserAddress")
    public Result showUserAddress(@RequestHeader("Authorization") String authorization) {
        if (authorization != null) {
            // 通过 Feign 获取用户ID
            Integer userId = userClient.getStoreUser(authorization).getData().getUserId();
            // 返回用户地址
            return addressService.showUserAddress(userId);
        }
        return Result.fail().message("请求头未携带Authorization");
    }

    @PostMapping("/saveUserAddress")
    public Result saveUserAddress(@RequestHeader("Authorization") String authorization, @RequestBody StoreUserAddress userAddress) {
        if (authorization != null) {
            // 通过 Feign 获取用户ID
            Integer userId = userClient.getStoreUser(authorization).getData().getUserId();
            // 将用户ID为当前用户
            userAddress.setUserId(userId);
            // 返回是否保存成功
            return addressService.saveUserAddress(userAddress);
        }
        return Result.fail().message("保存地址失败");
    }

    @PutMapping("/updateUserAddress")
    public Result updateUserAddress(@RequestHeader("Authorization") String authorization, @RequestBody StoreUserAddress userAddress) {
        if (authorization != null) {
            // 返回是否修改成功
            return addressService.updateUserAddress(userAddress);
        }
        return Result.fail().message("修改地址失败");
    }

    @DeleteMapping("/deleteUserAddress")
    public Result deleteUserAddress(@RequestHeader("Authorization") String authorization, @PathParam("addressId") Integer addressId) {
        if (authorization != null) {
            // 返回是否删除成功
            return addressService.deleteUserAddress(addressId);
        }
        return Result.fail().message("删除地址失败");
    }
}
