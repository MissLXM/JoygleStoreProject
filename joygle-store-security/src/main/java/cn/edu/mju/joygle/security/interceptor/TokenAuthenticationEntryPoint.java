package cn.edu.mju.joygle.security.interceptor;

import cn.edu.mju.joygle.common.constants.HttpStatus;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName: SecurityAuthenticationEntryPoint
 * Package: cn.edu.mju.joygle.security
 * Description: 无效token异常处理
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/24--15:04
 */
@Slf4j
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("SecurityAuthenticationEntryPoint.commence业务结束,结果:{}","token未携带或toke已过期");
        // 设置回写的编码、状态码、文本格式
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.ERROR);
        response.setContentType("application/json; charset=utf-8");
        // 回写
        PrintWriter out = response.getWriter();
        out.print("token未携带或toke已过期");
    }
}
