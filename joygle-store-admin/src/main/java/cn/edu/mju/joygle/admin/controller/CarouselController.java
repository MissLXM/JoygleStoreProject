package cn.edu.mju.joygle.admin.controller;

import cn.edu.mju.joygle.admin.service.CarouselService;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.pojo.StoreCarousel;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: CarouselController
 * Package: cn.edu.mju.joygle.admin.controller
 * Description: 轮播图管理控制层
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--13:00
 */
@RestController
@RequestMapping("/admin/carousel")
@Tag(name = "CarouselController", description = "轮播图服务管理")
public class CarouselController {

    @Setter(onMethod_ = @Autowired)
    private CarouselService carouselService;

    @GetMapping("/show/{currentPage}/{pageSize}")
    @Tag(name = "carouselShow", description = "轮播图展示")
    public Result carouselShow(
            @PathVariable("currentPage") Integer currentPage,
            @PathVariable("pageSize") Integer pageSize) {
        return carouselService.carouselShow(currentPage,pageSize);
    }

    @PostMapping("/save")
    @Tag(name = "carouselSave", description = "轮播图保存")
    public Result carouselSave(@RequestBody @Validated StoreCarousel carousel, BindingResult result) {
        if (result.hasErrors()) {
            return Result.fail().message("保存参数不合法");
        }
        return carouselService.carouselSave(carousel);
    }

    @PutMapping("/update")
    @Tag(name = "carouselUpdate", description = "轮播图修改")
    public Result carouselUpdate(@RequestBody @Validated StoreCarousel carousel, BindingResult result) {
        if (result.hasErrors()) {
            return Result.fail().message("修改参数不合法");
        }
        return carouselService.carouselUpdate(carousel);
    }

    @DeleteMapping("/delete/{carouselId}")
    @Tag(name = "carouselDelete", description = "轮播图删除")
    public Result carouselDelete(@PathVariable("carouselId") Integer carouselId) {
        return carouselService.carouselDelete(carouselId);
    }

    @DeleteMapping("/deleteByCarouselIds")
    @Tag(name = "deleteCarouselIds", description = "多轮播图删除")
    public Result deleteCarouselIds(@RequestParam("carouselIds") List<Integer> carouselIds) {
        return carouselService.deleteCarouselIds(carouselIds);
    }
}
