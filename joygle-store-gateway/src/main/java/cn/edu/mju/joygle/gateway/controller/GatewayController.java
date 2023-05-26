package cn.edu.mju.joygle.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: GatewayController
 * Package: cn.edu.mju.joygle.gateway.controller
 * Description: 测试类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/22--10:49
 */
@Slf4j
@RestController
public class GatewayController {

    @GetMapping("/test")
    public String demo() {
        return "hello,test";
    }
}
