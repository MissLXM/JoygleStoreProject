package cn.edu.mju.joygle.common.client.product;

import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.param.product.ProductIdsParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * ClassName: ProductClient
 * Package: cn.edu.mju.joygle.common.client.product
 * Description: 商品客户端
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/29--0:29
 */
@FeignClient("product-service")
public interface ProductClient {

    @GetMapping("/product/selectByProductIds")
    Result selectByProductIds(@RequestHeader("Authorization") String authorization, @RequestBody ProductIdsParam productIdsParam);

    @GetMapping("/product/productInfoShow/{productId}")
    Result productInfoShow(@RequestHeader("Authorization") String authorization,@PathVariable Integer productId);
}
