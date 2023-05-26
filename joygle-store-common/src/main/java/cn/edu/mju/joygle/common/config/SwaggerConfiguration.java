package cn.edu.mju.joygle.common.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * ClassName: SwaggerConfiguration
 * Package: cn.edu.mju.joygle.static.config
 * Description: Swagger接口文档配置
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/20--11:05
 */
@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openApi() {

        return new OpenAPI()
                // 标题
                .info(new Info().title("Joygle-Store-Project API")
                // 描述信息
                .description("乐购商城平台--接口测试文档")
                // 版本
                .version("v1.0.0")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringDoc Wiki Documentation")
                        .url("https://springdoc.org/v2"));

    }
}
