package cn.edu.mju.joygle.common.client.category;

import cn.edu.mju.joygle.common.core.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * ClassName: CategoryClient
 * Package: cn.edu.mju.joygle.common.client.category
 * Description: 类别客户端
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/29--0:57
 */
@FeignClient(value = "category-service")
public interface CategoryClient {

    /**
     * 通过一级类别查询二级类别
     * @param authorization 认证信息
     * @param categoryParentId 一级类别
     * @return 结果集
     */
    @GetMapping("/category/twoLevelCategoryShow/{categoryParentId}")
    Result twoLevelCategoryShow(@RequestHeader("Authorization") String authorization, @PathVariable("categoryParentId") Integer categoryParentId);

}
