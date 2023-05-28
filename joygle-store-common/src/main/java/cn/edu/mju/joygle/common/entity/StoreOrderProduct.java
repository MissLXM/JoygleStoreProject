package cn.edu.mju.joygle.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * ClassName: StoreOrderProduct
 * Package: cn.edu.mju.joygle.common.entity
 * Description: 订单商品实体类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/20--11:01
 */
@Data
@TableName("store_order_product")
@Schema(name = "StoreOrderProduct",description = "订单商品表")
public class StoreOrderProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "order_product_id",type = IdType.AUTO)
    @Schema(name = "orderProductId",description = "订单商品ID")
    private Integer orderProductId;

    @TableField("order_id")
    @Schema(name = "orderId",description = "订单ID")
    private String orderId;

    @TableField("product_id")
    @Schema(name = "productId",description = "商品ID")
    private Integer productId;

    @TableField("product_number")
    @Schema(name = "productNumber",description = "商品数量")
    private Integer productNumber;

    @TableField("product_price")
    @Schema(name = "productPrice",description = "商品总价格 = 商品数量 * 商品ID的价格")
    private Double productPrice;

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
