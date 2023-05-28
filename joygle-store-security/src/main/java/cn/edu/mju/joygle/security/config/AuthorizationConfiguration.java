package cn.edu.mju.joygle.security.config;

import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.security.handler.CustomAuthenticationEntryPoint;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: AuthorizationConfiguration
 * Package: cn.edu.mju.joygle.security.config
 * Description: 认证配置类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/23--21:49
 */
@Slf4j
@Configuration
@EnableAuthorizationServer
public class AuthorizationConfiguration extends AuthorizationServerConfigurerAdapter {

    /**
     * 客户端数据源
     */
    @Setter(onMethod_ = @Autowired)
    private DataSource dataSource;

    /**
     * 认证管理器
     */
    @Setter(onMethod_ = @Autowired)
    private AuthenticationManager authenticationManager;

    /**
     * 客户端异常
     */
    @Setter(onMethod_ = @Autowired)
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    /**
     * 令牌持久化对象(Jwt存储)
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 令牌的编码
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        // 通过证书得到一对密钥
        KeyStoreKeyFactory keyFactory = new KeyStoreKeyFactory(new ClassPathResource("joygle.jks"),"joygle-wjh".toCharArray());
        // 设置密钥
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyFactory.getKeyPair("joygle-wjh"));
        return converter;
    }

    /**
     * 读取数据库中的数据然后注入客户端中
     * @return
     */
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }


    /**
     * 登陆失败处理
     * @param e
     * @return
     */
    private ResponseEntity handleException(Exception e) {
        return new ResponseEntity<>(Result.fail().message("token失效"), HttpStatus.FOUND);
    }


    /**
     * 读取数据库中 oauth_client_details ,用来配置客户端
     * @param clients the client details configurer
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    /**
     * 权限设置
     * @param security a fluent configurer for security features
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        ClientCredentialsTokenEndpointFilter filter = new ClientCredentialsTokenEndpointFilter();
        // 客户端设置身份验证管理器
        filter.setAuthenticationManager(authenticationManager);
        // 客户端配置自定义异常处理
        filter.setAuthenticationEntryPoint(customAuthenticationEntryPoint);
        filter.afterPropertiesSet();

        security
                // 开启表单认证
                .allowFormAuthenticationForClients()
                // 开启 /oauth/token_key 验证端口     无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启 /oauth/check_token 验证端口   无权限访问
                .checkTokenAccess("permitAll()")
                // 添加自定义拦截器
                .addTokenEndpointAuthenticationFilter(filter);
    }


    /**
     * 服务端设置
     * @param endpoints the endpoints configurer
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // 设置令牌存储方式
                .tokenStore(tokenStore())
                // 设置Jwt令牌扩展器
                .tokenEnhancer(jwtAccessTokenConverter())
                // 设置身份验证管理器
                .authenticationManager(authenticationManager)
                // 异常翻译器(登录失败)
                .exceptionTranslator(this::handleException)
                // 将生成token的接口换成另一个
                .pathMapping("/oauth/token","/oauth/login")
                ;

        // 配置 tokenServices参数
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        // 设置令牌存储方式
        tokenServices.setTokenStore(endpoints.getTokenStore());
        // 是否支持刷新令牌
        tokenServices.setSupportRefreshToken(false);
        // 对从数据库读取的数据进行设置
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        // 设置Jwt令牌扩展器
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        // 设置令牌的访问时长为 30 天
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30));

        // 配置Oauth2服务的TokenServices,用于令牌管理和授权等
        endpoints.tokenServices(tokenServices);
    }

}
