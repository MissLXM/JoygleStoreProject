package cn.edu.mju.joygle.category;

import cn.edu.mju.joygle.security.handler.TokenAuthenticationEntryPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * ClassName: CategoryApplication
 * Package: cn.edu.mju.joygle.category
 * Description: 类别启动类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/21--18:59
 */
@SpringBootApplication
@EnableFeignClients("cn.edu.mju.joygle.common")
@Import({
        TokenAuthenticationEntryPoint.class
})
public class CategoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(CategoryApplication.class, args);
    }
}
