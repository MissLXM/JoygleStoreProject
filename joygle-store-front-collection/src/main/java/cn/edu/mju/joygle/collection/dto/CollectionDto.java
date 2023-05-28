package cn.edu.mju.joygle.collection.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * ClassName: CollectionDto
 * Package: cn.edu.mju.joygle.collection.dto
 * Description: 收藏回显
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/28--12:36
 */
@Data
@Schema(name = "CollectionDto",description = "收藏回显")
public class CollectionDto {

    @Schema(name = "userId",description = "用户ID")
    private Integer userId;

    // @Schema(name = "productDtoList",description = "商品信息集合")
    // private List<ProductDto> productDtoList;

}
