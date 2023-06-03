package cn.edu.mju.joygle.common.entity.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * ClassName: StoreMenu
 * Package: cn.edu.mju.joygle.common.entity.pojo
 * Description: 菜单实体类
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--19:04
 */
@Data
@TableName("store_menu")
@Schema(name = "StoreMenu",description = "菜单表")
public class StoreMenu implements Serializable {

    public static final Long serialVersionUID = 1L;

    @TableId(value = "menu_id",type = IdType.AUTO)
    @Schema(name = "menuId",description = "菜单ID")
    private Integer menuId;

    @TableField(value = "menu_parent_id")
    @Schema(name = "menuParentId",description = "父菜单ID")
    private Integer menuParentId;

    @TableField(value = "menu_name")
    @Schema(name = "menuName",description = "菜单名称")
    private String menuName;

    @TableField("create_time")
    @Schema(name = "createTime",description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Date createTime;

    @TableField("update_time")
    @Schema(name = "updateTime",description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Date updateTime;

    @TableLogic
    @TableField("status")
    @Schema(name = "status",description = "状态:0表示正常,1表示禁用")
    private Byte status;
}
