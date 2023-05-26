package cn.edu.mju.joygle.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * ClassName: StoreCategory
 * Package: cn.edu.mju.joygle.common.entity
 * Description: 商品类别实体类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/20--10:57
 */
@Data
@TableName("store_category")
@Schema(name = "StoreCategory",description = "商品类别实体类")
public class StoreCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "category_id", type = IdType.AUTO)
    @Schema(name = "categoryId",description = "商品类别ID")
    private Integer categoryId;

    @TableField("category_parent_id")
    @Schema(name = "categoryParentId",description = "商品类别父ID")
    private Integer categoryParentId;

    @TableField("category_name")
    @Schema(name = "categoryName",description = "商品类别名称")
    private String categoryName;

    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    @Schema(name = "createTime",description = "创建时间")
    private Date createTime;

    @TableField("update_time")
    @Schema(name = "updateTime",description = "更新时间")
    private Date updateTime;

    @TableLogic
    @TableField("status")
    @Schema(name = "status",description = "状态:0表示正常,1表示禁用")
    private Byte status;
}
