package cn.edu.mju.joygle.admin.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * ClassName: DeleteCategoryIdsParams
 * Package: cn.edu.mju.joygle.admin.param
 * Description: 多类别参数
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/10--7:09
 */
@Data
public class DeleteCategoryIdsParams {

    @Schema(name= "categoryIds",description = "多类别ID")
    private List<Integer> categoryIds;
}
