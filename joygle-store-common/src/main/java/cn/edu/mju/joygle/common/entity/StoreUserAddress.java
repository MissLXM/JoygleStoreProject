package cn.edu.mju.joygle.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * ClassName: StoreUserAddress
 * Package: cn.edu.mju.joygle.common.entity
 * Description: 用户地址实体类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/20--10:28
 */
@Data
@TableName("store_user_address")
@Schema(name= "StoreUserAddress",description = "用户地址表")
public class StoreUserAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "address_id",type = IdType.AUTO)
    @Schema(name= "addressId",description = "地址ID")
    private Integer addressId;

    @TableField("user_id")
    @Schema(name= "userId",description = "用户ID")
    private Integer userId;

    @TableField("name")
    @Schema(name= "name",description = "收货人姓名")
    private String name;

    @TableField("phone")
    @Schema(name= "phone",description = "收货人联系方式")
    private String phone;

    @TableField("province")
    @Schema(name= "province",description = "省份")
    private String province;

    @TableField("city")
    @Schema(name= "city",description = "城市")
    private String city;

    @TableField("district")
    @Schema(name= "district",description = "区县")
    private String district;

    @TableField("address")
    @Schema(name= "address",description = "详细地址")
    private String address;

    @TableField("is_default")
    @Schema(name= "isDefault",description = "默认地址:0表示不是，1表示是")
    private Byte isDefault;

    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    @Schema(name= "createTime",description = "创建时间")
    private Date createTime;

    @TableField("update_time")
    @Schema(name= "updateTime",description = "更新时间")
    private Date updateTime;

    @TableLogic
    @TableField("status")
    @Schema(name= "status",description = "状态:0表示正常,1表示禁用")
    private Byte status;
}
