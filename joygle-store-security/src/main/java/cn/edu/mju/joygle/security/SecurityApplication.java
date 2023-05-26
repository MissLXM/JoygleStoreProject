package cn.edu.mju.joygle.security;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * ClassName: SecurityApplication
 * Package: cn.edu.mju.joygle.security
 * Description: 安全框架启动类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/22--10:28
 */
@SpringBootApplication
// 开启资源服务
@EnableResourceServer
public class SecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
}
