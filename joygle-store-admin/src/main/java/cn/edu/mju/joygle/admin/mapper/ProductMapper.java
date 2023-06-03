package cn.edu.mju.joygle.admin.mapper;

import cn.edu.mju.joygle.common.entity.pojo.StoreProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: ProductMapper
 * Package: cn.edu.mju.joygle.admin.mapper
 * Description: 商品映射
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--19:18
 */
@Mapper
public interface ProductMapper extends BaseMapper<StoreProduct> {

}
