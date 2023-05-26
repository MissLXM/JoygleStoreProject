package cn.edu.mju.joygle.security.handler;

import cn.edu.mju.joygle.common.core.domain.Result;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.ClientAuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: CustomAuthenticationEntryPoint
 * Package: cn.edu.mju.joygle.security.handler
 * Description: 客户端异常处理
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/26--14:05
 */
@Slf4j
@Component
public class CustomAuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.error(e.getMessage());

        if(e instanceof InsufficientAuthenticationException){

            //如果是client_id和client_secret相关异常 返回自定义的数据格式
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setHeader("Content-Type", "application/json;charset=UTF-8");

            // 自定义结果集返回
            Result result = Result.fail(false).message("客户端信息错误").code(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(JSONUtil.toJsonStr(result));
        }else {
            super.commence(request,response,e);
        }

    }
}
