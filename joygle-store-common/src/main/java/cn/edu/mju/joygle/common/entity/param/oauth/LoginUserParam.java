package cn.edu.mju.joygle.common.entity.param.oauth;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * ClassName: LoginUserParam
 * Package: cn.edu.mju.joygle.common.param
 * Description: 用户登录参数
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/25--3:41
 */
@Data
@Tag(name = "LoginUserParam",description = "用户登录参数")
public class LoginUserParam {

    @NotBlank
    @Schema(name = "username",description = "登录名称")
    private String username;

    @NotBlank
    @Schema(name = "password",description = "登录密码")
    private String password;
}
