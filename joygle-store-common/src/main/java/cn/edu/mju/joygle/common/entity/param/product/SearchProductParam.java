package cn.edu.mju.joygle.common.entity.param.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * ClassName: SearchProductParam
 * Package: cn.edu.mju.joygle.product.param
 * Description: 类别商品参数
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/29--0:38
 */
@Data
@Schema(name = "SearchProductParam",description = "查询商品参数")
public class SearchProductParam {

    @Schema(name = "keyword",description = "关键字")
    private String keyword;

    @Schema(name = "currentPage",description = "当前页码")
    private Integer currentPage;

    @Schema(name = "pageSize",description = "每页显示的商品数量")
    private Integer pageSize;
}
