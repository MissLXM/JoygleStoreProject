package cn.edu.mju.joygle.common.entity.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ClassName: StoreCarousel
 * Package: cn.edu.mju.joygle.common.entity
 * Description: 轮播图实体类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/20--10:52
 */
@Data
@TableName("store_carousel")
@Schema(name = "StoreCarousel",description = "轮播图表")
public class StoreCarousel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "carousel_id", type = IdType.AUTO)
    @Schema(name = "carouselId",description = "轮播图ID")
    private Integer carouselId;

    @TableField("image_path")
    @Schema(name = "imagePath",description = "轮播图图片地址")
    private String imagePath;

    @TableField("product_id")
    @Schema(name = "productId",description = "商品ID")
    private Integer productId;

    @TableField("priority")
    @Schema(name = "priority",description = "显示优先级")
    private String priority;

    @TableField("create_time")
    @Schema(name = "createTime",description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Timestamp createTime;

    @TableField("update_time")
    @Schema(name = "updateTime",description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Timestamp updateTime;

    @TableLogic
    @TableField("status")
    @Schema(name = "status",description = "状态:0表示正常,1表示禁用")
    private Byte status;
}
