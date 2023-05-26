package cn.edu.mju.joygle.security.handler;

import cn.edu.mju.joygle.common.core.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ClassName: InvalidGrantExceptionHandler
 * Package: cn.edu.mju.joygle.security.handler
 * Description: 用户信息错误处理器
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/26--14:50
 */
@Slf4j
@RestControllerAdvice
public class InvalidGrantExceptionHandler {

    @ExceptionHandler(InvalidGrantException.class)
    public Result invalidGrantExceptionHandler(InvalidGrantException exception) {
        // 打印异常信息
        log.error(exception.getMessage());
        // 自定义返回结果集
        return Result.fail(false).message("用户名或密码错误");
    }
}
