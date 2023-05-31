package cn.edu.mju.joygle.common.entity.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * ClassName: UserAddressDto
 * Package: cn.edu.mju.joygle.address.dto
 * Description: 用户地址回显
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/26--23:34
 */
@Data
// 开启链式编程
@Accessors(chain = true)
@Tag(name = "UserAddressDto",description = "用户地址回显")
public class UserAddressDto {

    @Schema(name = "userId",description = "用户ID")
    private Integer userId;

    @Schema(name = "addressList",description = "用户所有地址")
    private List<AddressDto> addressList;
}
