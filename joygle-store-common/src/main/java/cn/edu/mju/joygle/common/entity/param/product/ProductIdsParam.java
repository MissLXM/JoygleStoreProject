package cn.edu.mju.joygle.common.entity.param.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: ProductIdsParam
 * Package: cn.edu.mju.joygle.common.param.product
 * Description: 多商品ID参数
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/29--0:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ProductIdsParam",description = "多商品ID参数")
public class ProductIdsParam {

    @Schema(name = "currentPage",description = "当前页码")
    private Integer currentPage;

    @Schema(name = "pageSize",description = "每页显示的商品数量")
    private Integer pageSize;

    @Schema(name = "productIds",description = "多商品ID")
    private List<Integer> productIds;
}
