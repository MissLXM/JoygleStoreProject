package cn.edu.mju.joygle.security.handler;

import cn.edu.mju.joygle.common.constants.HttpStatus;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: LoginAuthenticationHandler
 * Package: cn.edu.mju.joygle.security.handler
 * Description:
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/24--15:22
 */
@Slf4j
@Component
public class LoginAuthenticationHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException, ServletException {
        // 登录成功处理逻辑
        res.setCharacterEncoding("UTF-8");
        res.setStatus(HttpStatus.UNAUTHORIZED);
        res.setContentType("application/json; charset=utf-8");

        log.info("LoginAuthenticationHandler.onAuthenticationSuccess业务结束,Result结果:{}",Result.ok(auth).message("登录成功").code(HttpStatus.SUCCESS));
        // 回写登录成功信息
        res.getWriter().write(JSONUtil.toJsonStr(Result.ok(auth).message("登录成功").code(HttpStatus.SUCCESS)));
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {

        // 登录失败处理逻辑
        res.setCharacterEncoding("UTF-8");
        res.setStatus(HttpStatus.UNAUTHORIZED);
        res.setContentType("application/json; charset=utf-8");

        log.info("LoginAuthenticationHandler.onAuthenticationSuccess业务结束,Result结果:{}",Result.ok(e).message("登录失败").code(HttpStatus.ERROR));
        // 回写登录成功信息
        res.getWriter().write(JSONUtil.toJsonStr(Result.ok(e).message("登录失败").code(HttpStatus.ERROR)));
    }
}