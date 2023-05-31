package cn.edu.mju.joygle.orders.handler;

import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.orders.exception.OrdersException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ClassName: OrdersExceptionHandler
 * Package: cn.edu.mju.joygle.orders.handler
 * Description: 订单异常处理
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/31--0:25
 */
@RestControllerAdvice
public class OrdersExceptionHandler {

    @ExceptionHandler(value = OrdersException.class)
    public Result orderExceptionHandler(OrdersException e) {
        return Result.fail().message(e.getMessage());
    }
}
