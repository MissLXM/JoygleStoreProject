package cn.edu.mju.joygle.security.handler;
import cn.edu.mju.joygle.common.constants.HttpStatus;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.hutool.json.JSONUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: CustomAccessDeniedHandler
 * Package: cn.edu.mju.joygle.security.handler
 * Description: 权限不足异常类重写
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/24--15:20
 */
@Component("customAccessDeniedHandler")
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) {
        response.setStatus(HttpStatus.SUCCESS);
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            response.getWriter().print(JSONUtil.toJsonStr(
                    Result.ok().message("token非法或已经失效").code(HttpStatus.FORBIDDEN)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}