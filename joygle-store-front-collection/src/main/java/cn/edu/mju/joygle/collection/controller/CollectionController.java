package cn.edu.mju.joygle.collection.controller;

import cn.edu.mju.joygle.collection.service.CollectionService;
import cn.edu.mju.joygle.common.client.user.UserClient;
import cn.edu.mju.joygle.common.core.domain.Result;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: CollectionController
 * Package: cn.edu.mju.joygle.collection.controller
 * Description: 收藏控制层
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/29--0:17
 */
@RestController
@RequestMapping("/collection")
@Tag(name = "CollectionController", description = "用户收藏控制层")
public class CollectionController {

    @Setter(onMethod_ = @Autowired)
    private UserClient userClient;

    @Setter(onMethod_ = @Autowired)
    private CollectionService collectionService;

    @GetMapping("/showUserCollection/{currentPage}/{pageSize}")
    @Tag(name = "showUserCollection", description = "用户收藏展示")
    public Result showUserCollection(@RequestHeader("Authorization") String authorization, @PathVariable Integer currentPage,@PathVariable Integer pageSize) {
        if (authorization != null) {
            Integer userId = userClient.getStoreUser(authorization).getData().getUserId();
            return collectionService.showUserCollection(authorization,userId,currentPage,pageSize);
        }
        return Result.fail().message("token未携带或已过期");
    }

    @PostMapping("/saveUserCollection/{productId}")
    @Tag(name = "saveUserCollection", description = "用户收藏保存")
    public Result saveUserCollection(@RequestHeader("Authorization") String authorization, @PathVariable Integer productId) {
        if (authorization != null) {
            Integer userId = userClient.getStoreUser(authorization).getData().getUserId();
            return collectionService.saveUserCollection(userId, productId);
        }
        return Result.fail().message("token未携带或已过期");
    }

    @DeleteMapping("/deleteUserCollection/{productId}")
    @Tag(name = "deleteUserCollection", description = "用户收藏删除")
    public Result deleteUserCollection(@RequestHeader("Authorization") String authorization, @PathVariable Integer productId) {
        if (authorization != null) {
            Integer userId = userClient.getStoreUser(authorization).getData().getUserId();
            return collectionService.deleteUserCollection(userId, productId);
        }
        return Result.fail().message("token未携带或已过期");
    }

}
