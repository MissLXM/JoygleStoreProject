package cn.edu.mju.joygle.product.config;

import cn.edu.mju.joygle.security.handler.TokenAuthenticationEntryPoint;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * ClassName: ResourceServerAutoConfiguration
 * Package: cn.edu.mju.joygle.category.config
 * Description: 资源配置类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/23--21:57
 */
@Configuration
// 开启资源服务
@EnableResourceServer
// 启动方法级别控制
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerAutoConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * 令牌持久化服务
     */
    @Autowired
    private TokenStore tokenStore;

    /**
     * token认证处理器
     */
    @Setter(onMethod_ = @Autowired)
    private TokenAuthenticationEntryPoint tokenAuthenticationEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                // 关闭CSRF服务
                .csrf().disable()
                // 所有请求都拦截
                .authorizeRequests()
                .antMatchers("/**").authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources)  {
        // 设置令牌对象(Jwt令牌来验证并控制用户的访问)
        resources.tokenStore(tokenStore)
                // token 是否携带
                .authenticationEntryPoint(tokenAuthenticationEntryPoint);
    }
}
