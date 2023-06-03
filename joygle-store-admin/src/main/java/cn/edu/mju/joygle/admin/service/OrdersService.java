package cn.edu.mju.joygle.admin.service;

import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.pojo.StoreUserOrders;

/**
 * ClassName: OrdersService
 * Package: cn.edu.mju.joygle.admin.service
 * Description: 订单业务接口
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--19:21
 */
public interface OrdersService {

    /**
     * 订单展示
     * @param currentPage 当前页码
     * @param pageSize 每页显示数
     * @param keyword 搜索关键字
     * @return 结果集
     */
    Result ordersInfoShow(Integer currentPage, Integer pageSize,String keyword);

    /**
     * 订单删除
     * @param ordersId 订单ID
     * @return 结果集
     */
    Result ordersInfoDelete(Integer ordersId);
}
