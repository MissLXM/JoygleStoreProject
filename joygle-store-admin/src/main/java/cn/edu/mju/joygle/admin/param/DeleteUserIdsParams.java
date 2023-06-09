package cn.edu.mju.joygle.admin.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * ClassName: DeleteUserIdsParams
 * Package: cn.edu.mju.joygle.admin.param
 * Description: 多用户参数
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/10--7:06
 */
@Data
public class DeleteUserIdsParams {

    @Schema(name= "userIds",description = "多用户ID")
    private List<Integer> userIds;
}
