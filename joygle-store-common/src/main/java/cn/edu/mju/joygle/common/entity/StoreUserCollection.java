package cn.edu.mju.joygle.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * ClassName: StoreUserCollection
 * Package: cn.edu.mju.joygle.common.entity
 * Description: 用户收藏实体类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/20--10:34
 */
@Data
@TableName("store_user_collection")
@Schema(name= "StoreUserCollection",description = "用户收藏表")
public class StoreUserCollection implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "collection_id",type = IdType.AUTO)
    @Schema(name= "collectionId",description = "收藏ID")
    private Integer collectionId;

    @TableField("user_id")
    @Schema(name= "userId",description = "用户ID")
    private Integer userId;

    @TableField("product_id")
    @Schema(name= "productId",description = "商品ID")
    private Integer productId;

    @TableField("collection_time")
    @Schema(name= "collectionTime",description = "收藏时间")
    private Date collectionTime;
}
