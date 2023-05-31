package cn.edu.mju.joygle.common.entity.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * ClassName: StoreAdmin
 * Package: cn.edu.mju.joygle.common.entity
 * Description: 管理员实体类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/20--10:44
 */
@Data
@TableName("store_admin")
@Schema(name = "StoreAdmin",description = "管理员表")
public class StoreAdmin implements Serializable {

    public static final Long serialVersionUID = 1L;

    @TableId(value = "admin_id",type = IdType.AUTO)
    @Schema(name = "adminId",description = "管理员ID")
    private Integer adminId;

    @TableField("username")
    @Schema(name = "username",description = "登录名称")
    private String username;

    @TableField("password")
    @Schema(name = "password",description = "登录密码")
    private String password;

    @TableField("admin_name")
    @Schema(name = "adminName",description = "管理员姓名")
    private String adminName;

    @TableField("create_time")
    @Schema(name = "createTime",description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Date createTime;

    @TableField("update_time")
    @Schema(name = "updateTime",description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Date updateTime;

    @TableField("admin_role")
    @Schema(name = "adminRole",description = "管理员角色")
    private String adminRole;

    @TableLogic
    @TableField("status")
    @Schema(name = "status",description = "状态:0表示正常,1表示禁用")
    private Byte status;
}
