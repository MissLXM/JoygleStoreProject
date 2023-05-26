package cn.edu.mju.joygle.gateway.filter;

import cn.edu.mju.joygle.gateway.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * ClassName: TokenFilter
 * Package: cn.edu.mju.joygle.gateway.filter
 * Description: 全局拦截器(是否携带token)
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/23--13:10
 */
@Slf4j
//@Component 测试使用的
public class TokenFilter implements GlobalFilter, Ordered {

    // 定义Token
    private static final String AUTHORIZE_TOKEN = "access_token";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 放行用户的接口
        if (request.getURI().getPath().startsWith("/user/register")) {
            log.info("TokenFilter.filter业务结束,结果:{}","无需令牌");
            return chain.filter(exchange);
        }

        // 获取令牌
        String token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);
        // 判断令牌是否存在
        if (token == null) {
            log.info("TokenFilter.filter业务结束,结果:{}","令牌不存在");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        // 解析令牌
        try {
            Claims claims = JwtUtil.parseJwt(token);
        } catch (Exception e) {
            e.printStackTrace();
            //解析失败
            log.info("TokenFilter.filter业务结束,结果:{}","解析令牌失败");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        // 放行
        log.info("TokenFilter.filter业务结束,结果:{}","放行");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
