package cn.edu.mju.joygle.security;


import cn.edu.mju.joygle.common.entity.param.oauth.LoginUserParam;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;

/**
 * ClassName: JwtTest
 * Package: cn.edu.mju.joygle.security
 * Description: 令牌测试类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/23--13:31
 */

public class JwtTest {

    @Test
    public void genPasswordTest() {
        // 加密
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
        // 判断是否一直
        System.out.println(new BCryptPasswordEncoder().matches("123456","$2a$10$m6wFICT1/SWD7vMkMG4I4eFnMI3m2FDHojXV8i/qR7NOFRBL.q/5W"));
    }

    @Test
    public void createJwtTest() {
        // 证书文件
        String key_location = "joygle.jks";
        // 密钥库密码
        String keystore_password = "joygle-wjh";
        // 访问证书路径
        ClassPathResource resource = new ClassPathResource(key_location);
        // 密钥工厂
        KeyStoreKeyFactory keyFactory = new KeyStoreKeyFactory(resource,keystore_password.toCharArray());
        // 密钥的密码(和别名要匹配)
        String key_password = "joygle-wjh";
        // 密钥别名
        String alias = "joygle-wjh";
        // 密钥对(密钥和公钥)
        KeyPair keyPair = keyFactory.getKeyPair(alias, key_password.toCharArray());
        // 私钥
        RSAPrivateKey aPrivate = (RSAPrivateKey) keyPair.getPrivate();
        // 定义信息
        LoginUserParam user = new LoginUserParam();
        user.setUsername("admin");
        user.setPassword("123456");
        // 生成token令牌
        Jwt jwt = JwtHelper.encode(user.toString(), new RsaSigner(aPrivate));
        // 取出token令牌
        System.out.println(jwt.getEncoded());
    }

    @Test
    public void parseJwtTest() {
        // token令牌
        String token
                = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.VXNlcih1c2VySWQ9MSwgdXNlcm5hbWU9YWRtaW4sIHJvbGVzPVVTRVIp." +
                "LaUAZhoK9FL6206FQDIWJ90T9RuOSxXny6Magw05LPBGEfvCm8WXbpgRwj8Fa7FZIS9hNj04H6a1Ob48HBD3iVmsKTBoYMLS" +
                "tmjC79KA0UTXh_MK2bg0lTBamnrAtrVNiPKcd37pxs-PDVjZwi4pzbA6SDHTXQKyKEnsAqIoR2M41aqJXr9GJ7dmBy2cHj1u" +
                "Qhb07psF0ogXmiaTdm16owTq13Qdn4En-Jnys7y_8ozxfxBLpWEduNgUX3qk3EcfMTn7ynADKOQtlQ26GOhNYbbVtS11Wkyk" +
                "KtX4L6fvPWZR3yvoEt4Wd5SdNH-kcSPFd0GkBhAVww4PBgMagGfc1Q";

        // 公钥
        String public_key = "-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3H8KIPvMm3JpOYJxX+wP\n" +
                "R2gNF+5duSDW3PfHNaRjeqsrzT1ia3ao9LqDrxfuic0g/5BV6Zy6P4ptb4xDsBxs\n" +
                "vhYbSvd280t9JSd8ux6AnNpPEswDtxIn/7XMxjbvzQ+6QEHxAx9GC7V1Ht43XaqE\n" +
                "wM06sW+uWOjvU1CC9xMcQvItroOFkah7qDsN2BvTD1l9JG3UpfEVo5YY0LdovrE5\n" +
                "kOERCgB5Q1y+PF+WCKjdqOVFPx1Xl3uFnGIwBPnUyjRIIyQmKyeQs/mC+Lym8Wtw\n" +
                "W2MczYa7jS0HymQ1+tbXEdQkgTy+aqXB5mR6h2cZ5PaTYZ0AdmT7bq1RPJQBO9Pa\n" +
                "2wIDAQAB\n" +
                "-----END PUBLIC KEY-----";
        // 获取jwt
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(public_key));
        // Jwt原始内容
        String claims = jwt.getClaims();
        System.out.println(claims);
        // Jwt令牌
        String encoded = jwt.getEncoded();
        System.out.println(encoded);
    }
}
