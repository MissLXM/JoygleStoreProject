package cn.edu.mju.joygle.address.config;


import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * ClassName: JwtConfiguration
 * Package: cn.edu.mju.joygle.address.config
 * Description: Jwt令牌配置类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/23--21:55
 */
@Configuration
public class JwtConfiguration {

    /**
     * 公钥
     */
    public static final String PUBLIC_KEY = "public.key";
    /**
     * 密钥库密码
     */
    public static final String SECRET_KEY = "joygle-wjh";

    @Setter(onMethod_ = @Autowired)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    /**
     * 令牌持久化服务
     * @return 生成Jwt令牌并进行解析校验
     */
    @Bean
    @Qualifier("tokenStore")
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    /**
     * 令牌的解码
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 公钥地址
        ClassPathResource resource = new ClassPathResource(PUBLIC_KEY);

        String publicKey;
        try {
            // 拿到公钥内容
            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 设置校验公钥
        converter.setVerifierKey(publicKey);
        // 设置证书签名密码
        converter.setSigningKey(SECRET_KEY);
        return converter;
    }
}
