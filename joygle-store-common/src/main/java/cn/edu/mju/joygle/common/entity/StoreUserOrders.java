package cn.edu.mju.joygle.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * ClassName: StoreUserOrders
 * Package: cn.edu.mju.joygle.common.entity
 * Description: 用户订单实体类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/20--10:36
 */
@Data
@TableName("store_user_orders")
@Schema(name= "StoreUserOrders",description = "用户订单表")
public class StoreUserOrders implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_orders_id",type = IdType.AUTO)
    @Schema(name= "userOrdersId",description = "用户订单ID")
    private Integer userOrdersId;

    @TableField("orders_id")
    @Schema(name= "ordersId",description = "总订单ID")
    private String ordersId;

    @TableField("order_id")
    @Schema(name= "orderId",description = "订单ID")
    private String orderId;

    @TableField("user_id")
    @Schema(name= "userId",description = "用户ID")
    private Integer userId;

    @TableField("address_id")
    @Schema(name= "addressId",description = "地址ID")
    private Integer addressId;

    @TableField("create_time")
    @Schema(name= "createTime",description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Date createTime;

    @TableField("update_time")
    @Schema(name= "updateTime",description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Date updateTime;

    @TableField("orders_price")
    @Schema(name= "ordersPrice",description = "总订单价格")
    private Double ordersPrice;

    @TableLogic
    @TableField("status")
    @Schema(name= "status",description = "状态:0表示正常,1表示禁用")
    private Byte status;
}
