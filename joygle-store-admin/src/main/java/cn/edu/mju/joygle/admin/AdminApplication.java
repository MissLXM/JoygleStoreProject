package cn.edu.mju.joygle.admin;

import cn.edu.mju.joygle.common.config.MybatisPlusConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * ClassName: AdminApplication
 * Package: cn.edu.mju.joygle.admin
 * Description: 后台管理启动类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/21--18:54
 */
@SpringBootApplication
@Import(
        MybatisPlusConfiguration.class
)
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
