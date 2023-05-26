package cn.edu.mju.joygle.security.config;

import cn.edu.mju.joygle.security.handler.CustomAuthenticationEntryPoint;
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
 * Package: cn.edu.mju.joygle.security.config
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
     * 客户端异常
     */
    @Setter(onMethod_ = @Autowired)
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                // 关闭CSRF服务
                .csrf().disable()
                .authorizeRequests()
                // 放行 oauth 下的接口
                .antMatchers("/oauth/login/**","/oauth/logout/**").permitAll()
                // 所有请求都拦截
                .antMatchers("/**").authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

        resources
                // 设置令牌对象(Jwt令牌来验证并控制用户的访问)
                .tokenStore(tokenStore)
                // 客户端异常处理
                .authenticationEntryPoint(customAuthenticationEntryPoint);
    }
}