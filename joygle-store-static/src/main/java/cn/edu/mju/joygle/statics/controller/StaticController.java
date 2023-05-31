package cn.edu.mju.joygle.statics.controller;

import cn.edu.mju.joygle.common.core.domain.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * ClassName: StaticController
 * Package: cn.edu.mju.joygle.statics.controller
 * Description: 静态资源控制层
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/30--13:01
 */
@RestController
@RequestMapping("/static")
@Tag(name = "StaticController", description = "静态资源控制层")
public class StaticController {

    @Value("${path.projectPath}")
    private String projectPath;

    @Value("${path.picturePath}")
    private String picturePath;
    @PostMapping("/downloadAvatar")
    @Tag(name = "downloadAvatar", description = "头像存储")
    public Result downloadAvatar(@RequestParam("avatar") MultipartFile avatarFile) {
        // 判空
        if (!avatarFile.isEmpty()) {

            // 获取项目根路径
            String rootPath = Objects.requireNonNull
                            (StaticController.class
                            .getResource("/")).getPath()
                    // 最前面的 / 删掉
                    .substring(1)
                    // 最后面的路径删掉
                    .replace("/joygle-store-static/target/classes/", "/");

            // 存储的头像地址
            File filePath = new File(projectPath);

            // 判断是否有该路径
            if (!filePath.exists()) {
                return Result.fail().message("没有该路径");
            }

            // 获取文件夹下的总文件数
            int count = Objects.requireNonNull(filePath.listFiles()).length;

            // 头像名称设置(文件数量 + 1)
            String avatarName = (count + 1) + "." + StringUtils.getFilenameExtension(avatarFile.getOriginalFilename());

            // 保存头像
            File file = new File(rootPath + projectPath,avatarName);
            try {
                avatarFile.transferTo(file);
            } catch (IOException e) {
                return Result.fail().message("图片下载失败");
            }

            // 返回新的图片地址
            return Result.ok(picturePath + avatarName).message("图片下载成功");
        } else {
            return Result.fail().message("图片为空");
        }

    }


}
