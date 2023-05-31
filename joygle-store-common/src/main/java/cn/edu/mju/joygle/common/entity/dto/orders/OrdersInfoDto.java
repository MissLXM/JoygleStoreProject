package cn.edu.mju.joygle.common.entity.dto.orders;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * ClassName: OrdersInfoDto
 * Package: cn.edu.mju.joygle.common.entity.dto.orders
 * Description: 订单详细信息回显
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/31--0:40
 */
@Data
@Accessors(chain = true)
@Schema(name = "OrdersInfoDto",description = "订单详细信息回显")
public class OrdersInfoDto {

    @Schema(name = "orderId",description = "订单ID")
    private Long orderId;

    @Schema(name = "address",description = "地址")
    private String address;

    @Schema(name = "orderDtos",description = "商品信息集合")
    private List<OrderDto> orderDtos;

    @Schema(name = "orderTotalPrice",description = "订单总价格")
    private Double orderTotalPrice;
}
