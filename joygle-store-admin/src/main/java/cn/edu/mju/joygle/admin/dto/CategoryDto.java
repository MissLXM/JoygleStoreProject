package cn.edu.mju.joygle.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * ClassName: CategoryDto
 * Package: cn.edu.mju.joygle.admin.dto
 * Description: 类别参数
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/10--7:37
 */
@Data
@Accessors(chain = true)
public class CategoryDto {

    @Schema(name= "categoryId",description = "一级类别ID")
    private Integer categoryId;

    @Schema(name= "categoryName",description = "一级类别名称")
    private String categoryName;

    @Schema(name = "createTime",description = "创建时间")
    private Timestamp createTime;

    @Schema(name = "updateTime",description = "更新时间")
    private Timestamp updateTime;

    @Schema(name = "status",description = "状态:0表示正常,1表示禁用")
    private Byte status;
}
