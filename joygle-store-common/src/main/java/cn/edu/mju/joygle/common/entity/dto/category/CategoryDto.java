package cn.edu.mju.joygle.common.entity.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * ClassName: CategoryDto
 * Package: cn.edu.mju.joygle.category.dto
 * Description: 类别回显
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/28--12:02
 */
@Data
@Schema(name = "CategoryDto",description = "类别回显")
public class CategoryDto {

    @Schema(name = "categoryId",description = "类别ID")
    private Integer categoryId;

    @Schema(name = "categoryParentId",description = "父类别ID")
    private Integer categoryParentId;

    @Schema(name = "categoryName",description = "类别名称")
    private String categoryName;
}
