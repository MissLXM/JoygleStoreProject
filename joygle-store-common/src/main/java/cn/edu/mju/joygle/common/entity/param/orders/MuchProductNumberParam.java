package cn.edu.mju.joygle.common.entity.param.orders;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * ClassName: MuchProductNumberParam
 * Package: cn.edu.mju.joygle.common.entity.param.orders
 * Description: 多商品数量参数
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/31--0:34
 */
@Data
@Accessors(chain = true)
@Schema(name = "MuchProductNumberParam",description = "多商品参数")
public class MuchProductNumberParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(name = "productId",description = "商品ID")
    private Integer productId;

    @Schema(name = "productNumber",description = "商品数量")
    private Integer productNumber;
}
