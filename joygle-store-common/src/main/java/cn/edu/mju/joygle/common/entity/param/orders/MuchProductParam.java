package cn.edu.mju.joygle.common.entity.param.orders;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * ClassName: MuchProductParam
 * Package: cn.edu.mju.joygle.common.entity.param.orders
 * Description: 多商品参数
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/30--23:43
 */
@Data
@Accessors(chain = true)
@Schema(name = "MuchProductParam",description = "多商品ID参数")
public class MuchProductParam {

    @Schema(name = "addressId",description = "地址ID")
    private Integer addressId;

    @Schema(name = "products",description = "多商品ID参数")
    private List<MuchProductNumberParam> products;
}
