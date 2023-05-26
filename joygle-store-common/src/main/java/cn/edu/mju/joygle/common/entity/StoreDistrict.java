package cn.edu.mju.joygle.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: StoreDistrict
 * Package: cn.edu.mju.joygle.common.entity
 * Description: 区域实体类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/26--15:05
 */
@Data
@TableName("store_district")
@Schema(name = "StoreDistrict",description = "区域实体类")
public class StoreDistrict implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "district_id",type = IdType.AUTO)
    @Schema(name = "districtId",description = "区域ID")
    private Long districtId;

    @TableField("district_parent_id")
    @Schema(name = "districtParentId",description = "父区域ID")
    private Long districtParentId;

    @TableField("district_name")
    @Schema(name = "districtName",description = "区域名")
    private String districtName;
}
