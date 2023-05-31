package cn.edu.mju.joygle.product;

import cn.edu.mju.joygle.common.config.MybatisPlusConfiguration;
import cn.edu.mju.joygle.security.handler.TokenAuthenticationEntryPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * ClassName: ProductApplication
 * Package: cn.edu.mju.joygle.product
 * Description: 商品启动类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/21--18:24
 */
@SpringBootApplication
@EnableFeignClients("cn.edu.mju.joygle.common")
@Import({
        TokenAuthenticationEntryPoint.class,
        MybatisPlusConfiguration.class
})
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
