package cn.edu.mju.joygle.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * ClassName: CategoryDtos
 * Package: cn.edu.mju.joygle.admin.dto
 * Description: 类别封装回显
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/10--7:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryDtos extends CategoryDto{

    @Schema(name= "categorySuns",description = "一级类别ID下的二级类别")
    private List<CategoryDto> categorySuns;
}
