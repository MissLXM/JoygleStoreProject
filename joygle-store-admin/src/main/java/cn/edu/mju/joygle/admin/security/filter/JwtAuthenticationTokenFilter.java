package cn.edu.mju.joygle.admin.security.filter;

import cn.edu.mju.joygle.admin.redis.Cache.RedisCache;
import cn.edu.mju.joygle.admin.security.domain.MyUserDetails;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * ClassName: JwtAuthenticationTokenFilter
 * Package: cn.edu.mju.joygle.admin.security.filter
 * Description: Token令牌过滤器
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--15:46
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    // 放行接口的路径列表
    private static final List<String> EXCLUDED_PATHS = Arrays.asList("/admin/login", "/admin/getCode", "/admin/logout");
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 获取当前请求路径
        String requestUrl = request.getRequestURI().substring(request.getContextPath().length());

        if (EXCLUDED_PATHS.contains(requestUrl)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 获取请求头的token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            // 自定义返回结果集
            extracted(response,"令牌未携带");
            return;
        }

        //解析token
        String adminName;
        try {
            Claims claims = JwtUtil.parseJwt(token);
            adminName = claims.getSubject();
        } catch (Exception e) {
            // 自定义返回结果集
            extracted(response,"令牌非法");
            return;
        }
        //从redis中获取用户信息
        String redisKey = "login:" + adminName;
        MyUserDetails userDetails = redisCache.getCacheObject(redisKey);

        if(Objects.isNull(userDetails)){
            // 自定义返回结果集
            extracted(response,"用户未登录");
            return;
        }

        //存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }

    /**
     * 结果集封装
     * @param response
     * @param message
     * @throws IOException
     */
    private static void extracted(HttpServletResponse response,String message) throws IOException {
        Result result = Result.fail().message(message);
        String resultJson = new ObjectMapper().writeValueAsString(result);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(resultJson);
    }
}