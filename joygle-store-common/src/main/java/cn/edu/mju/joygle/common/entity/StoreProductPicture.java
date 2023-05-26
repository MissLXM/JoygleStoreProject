package cn.edu.mju.joygle.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: StoreProductPicture
 * Package: cn.edu.mju.joygle.common.entity
 * Description: 商品图片实体类
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/20--10:21
 */
@Data
@TableName("store_product_picture")
@Schema(name = "StoreProductPicture",description = "商品图片表")
public class StoreProductPicture implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "picture_id",type = IdType.AUTO)
    @Schema(name = "pictureId",description = "图片ID")
    private Integer pictureId;

    @TableField("product_id")
    @Schema(name = "productId",description = "商品ID")
    private Integer productId;

    @TableField("product_picture")
    @Schema(name = "productPicture",description = "商品图片地址")
    private String productPicture;
}
