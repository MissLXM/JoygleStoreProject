package cn.edu.mju.joygle.common.entity.dto.orders;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * ClassName: OrderDto
 * Package: cn.edu.mju.joygle.common.entity.dto.orders
 * Description: 订单回显
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/31--0:59
 */
@Data
@Accessors(chain = true)
@Schema(name = "OrdersDto",description = "订单回显")
public class OrderDto {

    @Schema(name = "productId",description = "商品ID")
    private Integer productId;

    @Schema(name = "productName",description = "商品名称")
    private String productName;

    @Schema(name = "categoryId",description = "类别ID")
    private Integer categoryId;

    @Schema(name = "productTitle",description = "商品标题")
    private String productTitle;

    @Schema(name = "productIntro",description = "商品副标题")
    private String productIntro;

    @Schema(name = "productImage",description = "商品图片")
    private String productImage;

    @Schema(name = "productPrice",description = "商品价格")
    private Double productPrice;

    @Schema(name = "productSalePrice",description = "商品销售价格")
    private Double productSalePrice;

    @Schema(name= "productNumber",description = "商品数量")
    private Integer productNumber;

    @Schema(name= "productTotalPrice",description = "商品总价格")
    private Double productTotalPrice;
}
