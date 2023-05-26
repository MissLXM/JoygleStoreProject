package cn.edu.mju.joygle.security.config;

import cn.edu.mju.joygle.security.handler.CustomAuthenticationEntryPoint;
import cn.edu.mju.joygle.security.handler.LoginAuthenticationHandler;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * ClassName: SecurityConfiguration
 * Package: cn.edu.mju.joygle.security.config
 * Description: 安全框架配置类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/23--21:43
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * 自定义用户服务
     */
    @Setter(onMethod_ = @Autowired)
    private UserDetailsService userDetailsService;

    /**
     * 登录处理器
     */
    @Setter(onMethod_ = @Autowired)
    private LoginAuthenticationHandler loginAuthenticationHandler;

    /**
     * 客户端异常
     */
    @Setter(onMethod_ = @Autowired)
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    /**
     * 密码加密规则
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证管理器
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // 所有请求拦截
                .requestMatchers().anyRequest()
                .and()
                // 放行接口
                .authorizeRequests()
                .antMatchers("/oauth/login/**","/oauth/logout/**").permitAll()
                .and()
                .formLogin()
                // 登录成功处理器
                .successHandler(loginAuthenticationHandler)
                // 登录失败处理器
                .failureHandler(loginAuthenticationHandler)
                .and()
                // 添加异常处理器
                .exceptionHandling()
                // 权限异常处理器
                .authenticationEntryPoint(customAuthenticationEntryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // 自定义用户认证
                .userDetailsService(userDetailsService)
                // 密码加密规则
                .passwordEncoder(bCryptPasswordEncoder());
    }
}
