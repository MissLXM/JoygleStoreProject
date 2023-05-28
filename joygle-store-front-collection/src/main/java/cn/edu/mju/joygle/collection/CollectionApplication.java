package cn.edu.mju.joygle.collection;

import cn.edu.mju.joygle.security.handler.TokenAuthenticationEntryPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * ClassName: CollectionApplication
 * Package: cn.edu.mju.joygle.collection
 * Description: 收藏启动类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/21--19:00
 */
@SpringBootApplication
@EnableFeignClients("cn.edu.mju.joygle.common")
@Import({
        TokenAuthenticationEntryPoint.class
})
public class CollectionApplication {
    public static void main(String[] args) {
        SpringApplication.run(CollectionApplication.class, args);
    }
}
