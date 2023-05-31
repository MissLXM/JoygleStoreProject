package cn.edu.mju.joygle.product.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: ProductConfiguration
 * Package: cn.edu.mju.joygle.orders.config
 * Description: 订单序列化
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/31--17:20
 */
@Configuration
public class ProductConfiguration {

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
