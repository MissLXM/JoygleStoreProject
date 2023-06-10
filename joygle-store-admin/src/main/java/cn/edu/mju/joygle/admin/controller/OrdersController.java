package cn.edu.mju.joygle.admin.controller;

import cn.edu.mju.joygle.admin.service.OrdersService;
import cn.edu.mju.joygle.common.core.domain.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: OrdersController
 * Package: cn.edu.mju.joygle.admin.controller
 * Description: 订单管理控制层
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--13:01
 */
@RestController
@RequestMapping("/admin/orders")
@Tag(name = "OrdersController", description = "订单服务管理")
public class OrdersController {

    @Setter(onMethod_ = @Autowired)
    private OrdersService ordersService;

    @GetMapping("/show/{currentPage}/{pageSize}/{keyword}")
    @Tag(name = "ordersInfoShow", description = "订单展示")
    public Result ordersInfoShow(
            @PathVariable("currentPage") Integer currentPage,
            @PathVariable("pageSize") Integer pageSize,
            @PathVariable("keyword") String keyword) {
        return ordersService.ordersInfoShow(currentPage,pageSize,keyword);
    }

    @DeleteMapping("/delete/{ordersId}")
    @Tag(name = "ordersInfoDelete", description = "订单删除")
    public Result ordersInfoDelete(@PathVariable("ordersId") Integer ordersId) {
        return ordersService.ordersInfoDelete(ordersId);
    }

    @DeleteMapping("/deleteByOrdersIds")
    @Tag(name = "deleteByOrdersIds", description = "多订单删除")
    public Result deleteByOrdersIds(@RequestParam("ordersIds") List<Integer> ordersIds) {
        return ordersService.deleteByOrdersIds(ordersIds);
    }
}
