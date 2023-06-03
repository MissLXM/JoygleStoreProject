package cn.edu.mju.joygle.admin.controller;

import cn.edu.mju.joygle.admin.service.UserService;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.pojo.StoreUser;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: UserController
 * Package: cn.edu.mju.joygle.admin.controller
 * Description: 用户管理控制层
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--12:59
 */
@RestController
@RequestMapping("/admin/user")
@Tag(name = "UserController", description = "用户管理服务")
public class UserController {

    @Setter(onMethod_ = @Autowired)
    private UserService userService;

    @GetMapping("/show/{currentPage}/{pageSize}/{keyword}")
    @Tag(name = "userInfoShow", description = "用户展示")
    public Result userInfoShow(
            @PathVariable("currentPage") Integer currentPage,
            @PathVariable("pageSize") Integer pageSize,
            @PathVariable("keyword") String keyword) {
        return userService.userInfoShow(currentPage,pageSize,keyword);
    }

    @PostMapping("/save")
    @Tag(name = "userInfoShow", description = "用户展示")
    public Result userInfoSave(@RequestBody @Validated StoreUser user, BindingResult result) {
        if (result.hasErrors()) {
            return Result.fail().message("保存参数不合法");
        }
        return userService.userInfoSave(user);
    }

    @PutMapping("/update")
    @Tag(name = "userInfoUpdate", description = "用户修改")
    public Result userInfoUpdate(@RequestBody @Validated StoreUser user, BindingResult result) {
        if (result.hasErrors()) {
            return Result.fail().message("修改参数不合法");
        }
        return userService.userInfoUpdate(user);
    }

    @DeleteMapping("/delete/{userId}")
    @Tag(name = "userInfoDelete", description = "用户删除")
    public Result userInfoDelete(@PathVariable("userId") Integer userId) {
        return userService.userInfoDelete(userId);
    }
}
