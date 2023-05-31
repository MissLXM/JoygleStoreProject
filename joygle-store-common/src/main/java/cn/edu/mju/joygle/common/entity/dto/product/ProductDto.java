package cn.edu.mju.joygle.common.entity.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * ClassName: ProductDto
 * Package: cn.edu.mju.joygle.collection.dto
 * Description: 商品回显
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/28--12:40
 */
@Data
@Schema(name = "ProductDto",description = "商品回显")
public class ProductDto {

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

}
