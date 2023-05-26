package cn.edu.mju.joygle.address;

import cn.edu.mju.joygle.security.handler.TokenAuthenticationEntryPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;


/**
 * ClassName: AddressApplication
 * Package: cn.edu.mju.joygle.address
 * Description: 地址启动类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/21--18:56
 */
@SpringBootApplication
@EnableFeignClients
@Import({
        TokenAuthenticationEntryPoint.class
})
public class AddressApplication {
    public static void main(String[] args) {
        SpringApplication.run(AddressApplication.class, args);
    }
}
