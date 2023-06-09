package cn.edu.mju.joygle.common.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ClassName: StoreUserTrolley
 * Package: cn.edu.mju.joygle.common.entity
 * Description: 用户购物车实体类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/20--10:40
 */
@Data
// 开启链式编程
@Accessors(chain = true)
@TableName("store_user_trolley")
@Schema(name= "updateTime",description = "更新时间")
public class StoreUserTrolley implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "trolley_id",type = IdType.AUTO)
    @Schema(name= "trolleyId",description = "购物车ID")
    private Integer trolleyId;

    @TableField("user_id")
    @Schema(name= "userId",description = "用户ID")
    private Integer userId;

    @TableField("product_id")
    @Schema(name= "productId",description = "商品ID")
    private Integer productId;

    @TableField("product_number")
    @Schema(name= "productNumber",description = "商品数量")
    private Integer productNumber;

    @TableField("create_time")
    @Schema(name= "createTime",description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Timestamp createTime;

    @TableField("update_time")
    @Schema(name= "updateTime",description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Timestamp updateTime;
}
