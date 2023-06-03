package cn.edu.mju.joygle.admin.mapper;

import cn.edu.mju.joygle.common.entity.pojo.StoreUserOrders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: OrdersMapper
 * Package: cn.edu.mju.joygle.admin.mapper
 * Description: 订单映射
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--19:20
 */
@Mapper
public interface OrdersMapper extends BaseMapper<StoreUserOrders> {

}
