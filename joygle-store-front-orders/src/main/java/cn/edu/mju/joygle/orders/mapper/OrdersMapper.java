package cn.edu.mju.joygle.orders.mapper;

import cn.edu.mju.joygle.common.entity.pojo.StoreUserOrders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: OrdersMapper
 * Package: cn.edu.mju.joygle.orders.mapper
 * Description: 订单映射
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/30--23:35
 */
@Mapper
public interface OrdersMapper extends BaseMapper<StoreUserOrders> {
}
