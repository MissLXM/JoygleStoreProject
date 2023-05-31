package cn.edu.mju.joygle.orders.controller;

import cn.edu.mju.joygle.common.client.user.UserClient;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.param.orders.MuchProductParam;
import cn.edu.mju.joygle.orders.service.OrdersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: OrdersController
 * Package: cn.edu.mju.joygle.orders.controller
 * Description: 订单控制层
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/30--23:37
 */
@RestController
@RequestMapping("/orders")
@Tag(name = "OrdersController", description = "订单控制层")
public class OrdersController {

    @Setter(onMethod_ = @Autowired)
    private UserClient userClient;

    @Setter(onMethod_ = @Autowired)
    private OrdersService ordersService;

    @PostMapping("/orderShowPrice")
    @Tag(name = "orderShowPrice", description = "商品未生成前的展示")
    public Result orderShowPrice(@RequestHeader("Authorization") String authorization,
                                 @RequestBody MuchProductParam muchProductParam) {
        if (authorization != null) {
            Integer userId = userClient.getStoreUser(authorization).getData().getUserId();
            return ordersService.orderShowPrice(authorization, userId, muchProductParam);
        }
        return Result.fail().message("token未携带或已过期");
    }

    @GetMapping("/ordersShow")
    @Tag(name = "ordersShow", description = "订单展示")
    public Result ordersShow(@RequestHeader("Authorization") String authorization) {
        if (authorization != null) {
            Integer userId = userClient.getStoreUser(authorization).getData().getUserId();
            return ordersService.ordersShow(authorization,userId);
        }
        return Result.fail().message("token未携带或已过期");
    }


    @PostMapping("/muchProductOrderSave")
    @Tag(name = "muchProductOrderSave", description = "多商品生成订单")
    public Result muchProductOrderSave(@RequestHeader("Authorization") String authorization,
                                       @RequestBody MuchProductParam muchProductParam) {
        if (authorization != null) {
            Integer userId = userClient.getStoreUser(authorization).getData().getUserId();
            return ordersService.muchProductOrderSave(authorization, userId, muchProductParam);
        }
        return Result.fail().message("token未携带或已过期");
    }

}
