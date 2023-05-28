package cn.edu.mju.joygle.carousel;

import cn.edu.mju.joygle.security.handler.TokenAuthenticationEntryPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * ClassName: CarouselApplication
 * Package: cn.edu.mju.joygle.carousel
 * Description: 轮播图启动类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/21--18:37
 */
@SpringBootApplication
@EnableFeignClients("cn.edu.mju.joygle.common")
@Import({
        TokenAuthenticationEntryPoint.class
})
public class CarouselApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarouselApplication.class, args);
    }
}
