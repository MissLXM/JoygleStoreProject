package cn.edu.mju.joygle.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * ClassName: StoreUser
 * Package: cn.edu.mju.joygle.common.entity
 * Description: 用户实体类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/20--10:24
 */
@Data
@TableName("store_user")
@Schema(name = "StoreUser",description = "用户表")
public class StoreUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id",type = IdType.AUTO)
    @Schema(name= "user_id",description = "用户ID")
    private Integer userId;

    @NotBlank
    @TableField("username")
    @Schema(name= "username",description = "登录名称")
    private String username;

    @NotBlank
    @TableField("password")
    @Schema(name= "password",description = "登录密码")
    private String password;

    @NotBlank
    @TableField("nickname")
    @Schema(name= "nickname",description = "用户昵称")
    private String nickname;

    @Email
    @NotBlank
    @TableField("user_email")
    @Schema(name= "userEmail",description = "用户邮箱")
    private String userEmail;

    @NotBlank
    @TableField("user_phone")
    @Schema(name= "userPhone", description = "手机号码")
    private String userPhone;

    @TableField("user_avatar")
    @Schema(name= "userAvatar",description = "用户头像")
    private String userAvatar;

    @TableField("user_role")
    @Schema(name= "user_role",description = "用户角色")
    private String userRole = "USER";

    @TableField("create_time")
    @Schema(name= "createTime",description = "创建时间")
    private Date createTime;

    @TableField("update_time")
    @Schema(name= "updateTime",description = "更新时间")
    private Date updateTime;

    @TableLogic
    @TableField("status")
    @Schema(name= "status",description = "状态:0表示正常,1表示禁用")
    private Byte status;

}
