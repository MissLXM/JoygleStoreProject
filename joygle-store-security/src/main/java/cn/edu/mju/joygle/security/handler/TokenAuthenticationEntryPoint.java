package cn.edu.mju.joygle.security.handler;

import cn.edu.mju.joygle.common.core.domain.Result;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: TokenAuthenticationEntryPoint
 * Package: cn.edu.mju.joygle.security.handler
 * Description: token未携带
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/26--14:47
 */
@Slf4j
@Component
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.error(authException.getMessage());

        // 未携带token
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");

        // 自定义结果集返回
        Result result = Result.fail(false).message("token未携带或已过期").code(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(JSONUtil.toJsonStr(result));
    }
}
