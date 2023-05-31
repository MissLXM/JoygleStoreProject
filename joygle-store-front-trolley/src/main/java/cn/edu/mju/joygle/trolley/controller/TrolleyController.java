package cn.edu.mju.joygle.trolley.controller;

import cn.edu.mju.joygle.common.client.user.UserClient;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.trolley.service.TrolleyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: TrolleyController
 * Package: cn.edu.mju.joygle.trolley.controller
 * Description: 购物车控制层
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/29--0:59
 */
@RestController
@RequestMapping("/trolley")
@Tag(name = "TrolleyController", description = "用户购物车控制层")
public class TrolleyController {

    @Setter(onMethod_ = @Autowired)
    private UserClient userClient;

    @Setter(onMethod_ = @Autowired)
    private TrolleyService trolleyService;

    @GetMapping("/showUserTrolley")
    @Tag(name = "showUserTrolley", description = "用户购物车展示")
    public Result showUserTrolley(@RequestHeader("Authorization") String authorization) {
        if (authorization != null) {
            Integer userId = userClient.getStoreUser(authorization).getData().getUserId();
            return trolleyService.showUserTrolley(authorization,userId);
        }
        return Result.fail().message("token未携带或过期");
    }

    @PostMapping("/saveUserTrolley/{productId}")
    @Tag(name = "saveUserTrolley", description = "用户添加商品到购物车")
    public Result saveUserTrolley(@RequestHeader("Authorization") String authorization,@PathVariable Integer productId) {
        if (authorization != null) {
            Integer userId = userClient.getStoreUser(authorization).getData().getUserId();
            return trolleyService.saveUserTrolley(authorization,userId, productId);
        }
        return Result.fail().message("token未携带或过期");
    }

    @PutMapping("/updateUserTrolley/{productId}/{productNumber}")
    @Tag(name = "updateUserTrolley", description = "用户修改购物车中商品的数量")
    public Result updateUserTrolley(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Integer productId,
            @PathVariable Integer productNumber) {
        if (authorization != null) {
            Integer userId = userClient.getStoreUser(authorization).getData().getUserId();
            return trolleyService.updateUserTrolley(authorization, userId, productId,productNumber);
        }
        return Result.fail().message("token未携带或过期");
    }

    @DeleteMapping("/deleteUserTrolley/{productId}")
    @Tag(name = "deleteUserTrolley", description = "用户删除购物车中的商品")
    public Result deleteUserTrolley(@RequestHeader("Authorization") String authorization,@PathVariable Integer productId) {
        if (authorization != null) {
            Integer userId = userClient.getStoreUser(authorization).getData().getUserId();
            return trolleyService.deleteUserTrolley(authorization, userId, productId);
        }
        return Result.fail().message("token未携带或过期");
    }
}
