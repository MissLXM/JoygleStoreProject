package cn.edu.mju.joygle.common.entity.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Accessors(chain = true)
@Schema(name= "StoreUserOrders",description = "用户订单表")
public class StoreUserOrders implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "orders_id",type = IdType.AUTO)
    @Schema(name= "ordersId",description = "订单ID")
    private Integer ordersId;

    @TableField("order_id")
    @Schema(name= "orderId",description = "订单编号")
    private Long orderId;

    @TableField("user_id")
    @Schema(name= "userId",description = "用户ID")
    private Integer userId;

    @TableField("address_id")
    @Schema(name= "addressId",description = "地址ID")
    private Integer addressId;

    @TableField("product_id")
    @Schema(name= "productId",description = "商品ID")
    private Integer productId;

    @TableField("product_number")
    @Schema(name= "productNumber",description = "商品数量")
    private Integer productNumber;

    @TableField("product_price")
    @Schema(name= "productPrice",description = "商品价格 = 数量 * 实际价格")
    private Double productPrice;

    @TableField("create_time")
    @Schema(name= "createTime",description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Timestamp createTime;

    @TableField("update_time")
    @Schema(name= "updateTime",description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Timestamp updateTime;

    @TableLogic
    @TableField("status")
    @Schema(name= "status",description = "状态:0表示正常,1表示禁用")
    private Byte status;
}
