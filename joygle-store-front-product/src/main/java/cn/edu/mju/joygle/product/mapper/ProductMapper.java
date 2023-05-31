package cn.edu.mju.joygle.product.mapper;

import cn.edu.mju.joygle.common.entity.pojo.StoreProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: ProductMapper
 * Package: cn.edu.mju.joygle.product.mapper
 * Description: 商品映射
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/28--12:33
 */
@Mapper
public interface ProductMapper extends BaseMapper<StoreProduct> {

}
