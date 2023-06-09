package cn.edu.mju.joygle.admin.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * ClassName: DeleteOrdersIdsParams
 * Package: cn.edu.mju.joygle.admin.param
 * Description: 多订单参数
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/10--7:09
 */
@Data
public class DeleteOrdersIdsParams {

    @Schema(name= "ordersIds",description = "多订单ID")
    private List<Integer> ordersIds;
}
