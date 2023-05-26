package cn.edu.mju.joygle.user;

import cn.edu.mju.joygle.security.handler.LoginAuthenticationHandler;
import cn.edu.mju.joygle.security.interceptor.TokenAuthenticationEntryPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * ClassName: UserApplication
 * Package: cn.edu.mju.joygle.user
 * Description: 用户启动类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/20--11:02
 */
@SpringBootApplication
@EnableFeignClients
@Import(value = {
        TokenAuthenticationEntryPoint.class,
        LoginAuthenticationHandler.class
})
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
