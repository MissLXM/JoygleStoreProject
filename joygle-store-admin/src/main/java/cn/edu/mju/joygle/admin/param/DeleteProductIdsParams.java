package cn.edu.mju.joygle.admin.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * ClassName: DeleteProductIdsParams
 * Package: cn.edu.mju.joygle.admin.param
 * Description: 多商品参数
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/10--7:10
 */
@Data
public class DeleteProductIdsParams {

    @Schema(name= "productIds",description = "多商品ID")
    private List<Integer> productIds;
}
