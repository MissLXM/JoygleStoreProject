package cn.edu.mju.joygle.orders.exception;

/**
 * ClassName: OrdersException
 * Package: cn.edu.mju.joygle.orders.exception
 * Description: 订单异常
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/31--0:21
 */
public class OrdersException extends RuntimeException{

    String message;

    public OrdersException(String message) {
        super(message);
        this.message = message;
    }
}
