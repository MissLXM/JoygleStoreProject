package cn.edu.mju.joygle.common.entity.dto.trolley;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * ClassName: TrolleyDto
 * Package: cn.edu.mju.joygle.common.entity.dto.trolley
 * Description: 购物车商品信息
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/29--0:32
 */
@Data
@Schema(name = "TrolleyDto",description = "购物车回显")
public class TrolleyDto {

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

    @Schema(name = "productSales",description = "商品销量")
    private Integer productSales;

    @Schema(name = "productStock",description = "商品库存")
    private Long productStock;

    @Schema(name= "productNumber",description = "商品数量")
    private Integer productNumber;
}
