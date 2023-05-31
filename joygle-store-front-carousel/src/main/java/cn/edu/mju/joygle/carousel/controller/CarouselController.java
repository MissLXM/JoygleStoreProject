package cn.edu.mju.joygle.carousel.controller;

import cn.edu.mju.joygle.carousel.service.CarouselService;
import cn.edu.mju.joygle.common.core.domain.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: CarouselController
 * Package: cn.edu.mju.joygle.carousel.controller
 * Description: 轮播图控制层
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/28--12:46
 */
@Slf4j
@RestController
@RequestMapping("/carousel")
@Tag(name = "CarouselController", description = "轮播图控制层")
public class CarouselController {

    @Setter(onMethod_ = @Autowired)
    private CarouselService carouselService;

    @GetMapping("/defaultShow")
    @Tag(name = "defaultShow", description = "轮播图默认展示(6张)")
    public Result defaultShow(@RequestHeader("Authorization") String authorization) {
        if (authorization != null) {
            return carouselService.defaultShow();
        }
        return Result.fail().message("token为空或过期");
    }


    @GetMapping("/skipProductInfo/{categoryId}")
    @Tag(name = "skipProductInfo", description = "轮播图跳转商品详情")
    public Result skipProductInfo(@RequestHeader("Authorization") String authorization, @PathVariable Integer categoryId) {
        if (authorization != null) {
            return carouselService.skipProductInfo(authorization,categoryId);
        }
        return Result.fail().message("token为空或过期");
    }
}
