package cn.edu.mju.joygle.carousel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * ClassName: CarouselDto
 * Package: cn.edu.mju.joygle.carousel.dto
 * Description: 轮播图回显
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/28--12:03
 */
@Data
@Schema(name = "CarouselDto",description = "轮播图回显")
public class CarouselDto {

    @Schema(name = "carouselId",description = "轮播图ID")
    private Integer carouselId;

    @Schema(name = "imagePath",description = "轮播图图片地址")
    private String imagePath;

    @Schema(name = "productId",description = "商品ID")
    private Integer productId;
}
