package cn.edu.mju.joygle.admin.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * ClassName: DeleteCarouselIdsParams
 * Package: cn.edu.mju.joygle.admin.param
 * Description: 多轮播图参数
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/10--7:07
 */
@Data
public class DeleteCarouselIdsParams {

    @Schema(name= "carouselIds",description = "多轮播图ID")
    private List<Integer> carouselIds;
}
