package cn.edu.mju.joygle.common.entity.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * ClassName: AddressDto
 * Package: cn.edu.mju.joygle.address.dto
 * Description: 地址回显
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/26--23:37
 */
@Data
// 开启链式编程
@Accessors(chain = true)
@Tag(name = "AddressDto",description = "地址回显")
public class AddressDto {

    @Schema(name = "addressId",description = "地址ID")
    private Integer addressId;

    @Schema(name = "name",description = "姓名")
    private String name;

    @Schema(name = "phone",description = "手机号")
    private String phone;

    @Schema(name = "province",description = "省份")
    private String province;

    @Schema(name = "city",description = "城市")
    private String city;

    @Schema(name = "district",description = "区县")
    private String district;

    @Schema(name = "address",description = "详细地址")
    private String address;

    @Schema(name = "isDefault",description = "默认地址(0:不是、1:是)")
    private String isDefault;
}
