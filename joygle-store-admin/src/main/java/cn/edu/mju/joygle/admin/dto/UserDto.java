package cn.edu.mju.joygle.admin.dto;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Date;

/**
 * ClassName: UserDto
 * Package: cn.edu.mju.joygle.admin.dto
 * Description: 用户回显
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/4--5:54
 */
@Data
public class UserDto {

    @Schema(name= "user_id",description = "用户ID")
    private Integer userId;

    @Schema(name= "username",description = "登录名称")
    private String username;

    @Schema(name= "password",description = "登录密码")
    private String password;

    @Schema(name= "nickname",description = "用户昵称")
    private String nickname;

    @Schema(name= "userEmail",description = "用户邮箱")
    private String userEmail;

    @Schema(name= "userPhone", description = "手机号码")
    private String userPhone;

    @Schema(name= "createTime",description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Date createTime;

    @Schema(name= "updateTime",description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Date updateTime;

    @Schema(name= "status",description = "状态:0表示正常,1表示禁用")
    private Byte status;
}
