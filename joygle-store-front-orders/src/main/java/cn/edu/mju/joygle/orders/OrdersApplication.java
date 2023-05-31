package cn.edu.mju.joygle.orders;

import cn.edu.mju.joygle.security.handler.TokenAuthenticationEntryPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * ClassName: OrdersApplication
 * Package: cn.edu.mju.joygle.orders
 * Description: 订单启动类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/21--19:01
 */
@SpringBootApplication
@EnableFeignClients("cn.edu.mju.joygle.common")
@Import({
        TokenAuthenticationEntryPoint.class
})
public class OrdersApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);
    }

}
