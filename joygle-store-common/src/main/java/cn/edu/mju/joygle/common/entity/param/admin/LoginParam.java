package cn.edu.mju.joygle.common.entity.param.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * ClassName: LoginParam
 * Package: cn.edu.mju.joygle.common.entity.param.admin
 * Description: 登录参数
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--14:27
 */
@Data
@Tag(name = "LoginUserParam",description = "用户登录参数")
public class LoginParam {

    @NotBlank
    @Schema(name = "username",description = "登录名称")
    private String username;

    @NotBlank
    @Schema(name = "password",description = "登录密码")
    private String password;

    @Schema(name = "code",description = "验证码")
    private String code;
}