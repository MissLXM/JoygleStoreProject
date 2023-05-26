package cn.edu.mju.joygle.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * ClassName: LoginUserDto
 * Package: cn.edu.mju.joygle.security.dto
 * Description: 用户回显类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/25--3:44
 */
@Data
// 开启链式编程
@Accessors(chain = true)
@Tag(name = "LoginUserDto",description = "用户登录回显")
public class LoginUserDto {

    @Schema(name = "access_token",description = "令牌")
    private String accessToken;

    @Schema(name = "token_type",description = "令牌")
    private String tokenType;

    @Schema(name = "refresh_token",description = "令牌")
    private String refreshToken;

    @Schema(name = "expires_in",description = "令牌")
    private Integer expiresIn;

    @Schema(name = "scope",description = "令牌")
    private Set<String> scope;

    @Schema(name = "jti",description = "令牌")
    private String jti;
}
