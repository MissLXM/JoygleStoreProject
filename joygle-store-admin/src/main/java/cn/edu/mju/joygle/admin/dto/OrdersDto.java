package cn.edu.mju.joygle.admin.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Date;

/**
 * ClassName: OrdersDto
 * Package: cn.edu.mju.joygle.admin.dto
 * Description: 订单回显
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/4--5:55
 */
@Data
@Accessors(chain = true)
public class OrdersDto {

    @Schema(name= "ordersId",description = "订单ID")
    private Integer ordersId;

    @Schema(name= "orderId",description = "订单编号")
    private Long orderId;

    @Schema(name= "nickname",description = "用户名称")
    private String nickname;

    @TableField("product_id")
    @Schema(name= "productName",description = "商品名称")
    private String productName;

    @Schema(name= "productNumber",description = "商品数量")
    private Integer productNumber;

    @Schema(name= "productPrice",description = "商品价格 = 数量 * 实际价格")
    private Double productPrice;

    @Schema(name= "createTime",description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Date createTime;

    @Schema(name= "updateTime",description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Date updateTime;

    @Schema(name= "status",description = "状态:0表示正常,1表示禁用")
    private Byte status;
}
