package cn.edu.mju.joygle.admin.controller;

import cn.edu.mju.joygle.admin.mapper.MenuMapper;
import cn.edu.mju.joygle.admin.redis.Cache.RedisCache;
import cn.edu.mju.joygle.admin.service.AdminService;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.param.admin.LoginParam;
import cn.edu.mju.joygle.common.entity.pojo.StoreMenu;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * ClassName: AdminController
 * Package: cn.edu.mju.joygle.admin.controller
 * Description: 登录管理控制层
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--13:01
 */
@Slf4j
@RestController
@RequestMapping("/admin")
@Tag(name = "AdminController", description = "登录管理控制类")
public class AdminController {

    @Autowired
    private RedisCache redisCache;

    @Setter(onMethod_ = @Autowired)
    private MenuMapper menuMapper;

    @Setter(onMethod_ = @Autowired)
    private AdminService adminService;

    @PostMapping("/login")
    @Tag(name = "login", description = "登录")
    public Result login(@RequestBody @Validated LoginParam loginParam, BindingResult result) {
        if (result.hasErrors()) {
            return Result.fail().message("参数不合法");
        }

        // 验证码认证
        String captcha = (String) redisCache.getCacheObject("captcha");
        if (!Objects.equals(loginParam.getCode(), captcha)) {
            return Result.fail().message("验证码错误");
        }
        // 登录密码认证
        return adminService.login(loginParam);
    }

    @GetMapping("/logout")
    @Tag(name = "logout", description = "登出")
    public Result logout() {
        return adminService.logout();
    }

    @GetMapping("/getCode")
    @Tag(name = "getCode", description = "验证码生成")
    public Result getCode() {
        // 根据 hutool 工具包 生成验证码
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 30);

        // 设置验证码类型
        lineCaptcha.setGenerator(randomGenerator);

        // 输出生成的验证码
        log.info("生成的验证码:" + lineCaptcha.getImageBase64Data());
        log.info("生成的验证码:" + lineCaptcha.getCode());

        // 结果集封装
        if (lineCaptcha.getImageBase64Data() == null) {
            return Result.fail().message("验证码生成失败");
        }

        // 存入redis
        redisCache.setCacheObject("captcha", lineCaptcha.getCode());
        return Result.ok(lineCaptcha.getImageBase64Data()).message("验证码生成成功");
    }

    @GetMapping("/menuShow")
    @Tag(name = "getMenuShow", description = "获取菜单列表")
    public Result getMenuShow() {
        // 查询所有菜单
        List<StoreMenu> menus = menuMapper.selectList(null);

        // 判空
        if (menus.size() == 0) {
            return Result.fail().message("未查询到菜单");
        }

        return Result.ok(menus).message("查询菜单成功");
    }
}
