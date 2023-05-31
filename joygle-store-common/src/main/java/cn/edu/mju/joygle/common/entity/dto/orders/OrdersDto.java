package cn.edu.mju.joygle.common.entity.dto.orders;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * ClassName: OrdersDto
 * Package: cn.edu.mju.joygle.common.entity.dto.orders
 * Description: 订单回显
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/31--0:59
 */
@Data
@Accessors(chain = true)
@Schema(name = "OrdersDto",description = "订单回显")
public class OrdersDto {

    @Schema(name = "orderDtos",description = "商品信息集合")
    private List<OrderDto> orderDtos;

    @Schema(name = "orderTotalPrice",description = "订单总价格")
    private Double orderTotalPrice;
}
