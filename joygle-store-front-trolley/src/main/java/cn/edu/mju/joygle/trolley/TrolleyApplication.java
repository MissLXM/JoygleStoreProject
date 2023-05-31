package cn.edu.mju.joygle.trolley;

import cn.edu.mju.joygle.common.config.MybatisPlusConfiguration;
import cn.edu.mju.joygle.security.handler.TokenAuthenticationEntryPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * ClassName: TrolleyApplication
 * Package: cn.edu.mju.joygle.trolley
 * Description: 购物车启动类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/21--19:02
 */
@SpringBootApplication
@EnableFeignClients("cn.edu.mju.joygle.common")
@Import({
        TokenAuthenticationEntryPoint.class,
        MybatisPlusConfiguration.class
})
public class TrolleyApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrolleyApplication.class, args);
    }
}
