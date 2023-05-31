package cn.edu.mju.joygle.common.client.address;

import cn.edu.mju.joygle.common.core.domain.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * ClassName: AddressClient
 * Package: cn.edu.mju.joygle.common.client.address
 * Description: 地址客户端
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/31--0:57
 */
@FeignClient(value = "address-service")
public interface AddressClient {

    @GetMapping("/address/showUserAddressByAddressId/{addressId}")
    @Tag(name = "showUserAddressByAddressId", description = "查询地址")
    Result showUserAddressByAddressId(
            @RequestHeader("Authorization") String authorization, @PathVariable Integer addressId);
}
