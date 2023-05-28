package cn.edu.mju.joygle.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * ClassName: StoreProduct
 * Package: cn.edu.mju.joygle.common.entity
 * Description: 商品实体类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/20--10:16
 */
@Data
@TableName("store_product")
@Schema(name = "StoreProduct",description = "商品表")
public class StoreProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "product_id",type = IdType.AUTO)
    @Schema(name = "productId",description = "商品ID")
    private Integer productId;

    @TableField("product_name")
    @Schema(name = "productName",description = "商品名称")
    private String productName;

    @TableField("category_id")
    @Schema(name = "categoryId",description = "类别ID")
    private Integer categoryId;

    @TableField("product_title")
    @Schema(name = "productTitle",description = "商品标题")
    private String productTitle;

    @TableField("product_intro")
    @Schema(name = "productIntro",description = "商品副标题")
    private String productIntro;

    @TableField("product_price")
    @Schema(name = "productPrice",description = "商品价格")
    private Double productPrice;

    @TableField("product_sale_price")
    @Schema(name = "productSalePrice",description = "商品实际价格")
    private Double productSalePrice;

    @TableField("product_stock")
    @Schema(name = "productStock",description = "商品库存量")
    private Long productStock;

    @TableField("product_sales")
    @Schema(name = "productSales",description = "商品销售量")
    private Long productSales;

    @TableField("product_image")
    @Schema(name = "productImage",description = "商品图片")
    private String productImage;

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
