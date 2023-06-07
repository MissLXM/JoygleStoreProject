package cn.edu.mju.joygle.admin.service.impl;

import cn.edu.mju.joygle.admin.dto.OrdersDto;
import cn.edu.mju.joygle.admin.mapper.OrdersMapper;
import cn.edu.mju.joygle.admin.mapper.ProductMapper;
import cn.edu.mju.joygle.admin.mapper.UserMapper;
import cn.edu.mju.joygle.admin.service.OrdersService;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.pojo.StoreProduct;
import cn.edu.mju.joygle.common.entity.pojo.StoreUser;
import cn.edu.mju.joygle.common.entity.pojo.StoreUserOrders;
import cn.edu.mju.joygle.common.utils.StringUtils;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: OrdersServiceImpl
 * Package: cn.edu.mju.joygle.admin.service.impl
 * Description: 订单业务实现
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--19:21
 */
@Slf4j
@Service
public class OrdersServiceImpl implements OrdersService {

    @Setter(onMethod_ = @Autowired)
    private OrdersMapper ordersMapper;

    @Setter(onMethod_ = @Autowired)
    private UserMapper userMapper;

    @Setter(onMethod_ = @Autowired)
    private ProductMapper productMapper;

    /**
     * 订单展示
     * @param currentPage 当前页码
     * @param pageSize    每页显示数
     * @param keyword     搜索关键字
     * @return 结果集
     */
    @Override
    public Result ordersInfoShow(Integer currentPage, Integer pageSize, String keyword) {
        // 初始化数据
        IPage<StoreUserOrders> ipage = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<StoreUserOrders> wrapper = new LambdaQueryWrapper<>();

        // 判断是否有搜索
        if ("null".equals(keyword)) { keyword = null; }
        if (StringUtils.isNotEmpty(keyword)) {
            wrapper.like(StoreUserOrders::getOrderId, keyword);
        }

        // 查询
        IPage<StoreUserOrders> userOrdersIPage = ordersMapper.selectPage(ipage, wrapper);
        IPage<OrdersDto> userOrdersDtpPage = new Page<>(currentPage, pageSize);
        BeanUtil.copyProperties(userOrdersIPage,userOrdersDtpPage);

        // 封装结果集
        List<OrdersDto> userOrdersDto = new ArrayList<>();
        List<StoreUserOrders> userOrdersList = userOrdersIPage.getRecords();
        userOrdersList.forEach(userOrders -> {
            OrdersDto ordersDto = new OrdersDto();
            // 查询用户昵称
            String nickname = userMapper.selectOne(
                    new LambdaQueryWrapper<StoreUser>()
                            .eq(StoreUser::getUserId, userOrders.getUserId())).getNickname();
            // 查询商品名称
            String productName = productMapper.selectOne(
                    new LambdaQueryWrapper<StoreProduct>()
                            .eq(StoreProduct::getProductId, userOrders.getProductId())).getProductName();
            BeanUtil.copyProperties(userOrders, ordersDto);
            // 设置
            ordersDto
                    .setNickname(nickname)
                    .setProductName(productName);
            userOrdersDto.add(ordersDto);
            log.info("订单信息" + ordersDto);
        });
        if (userOrdersDto.size() == 0) {
            return Result.fail().message("查询失败");
        }
        userOrdersDtpPage.setRecords(userOrdersDto);
        return Result.ok(userOrdersDtpPage).message("查询成功");
    }


    /**
     * 订单删除
     * @param ordersId 订单ID
     * @return 结果集
     */
    @Override
    public Result ordersInfoDelete(Integer ordersId) {
        // 删除
        int rows = ordersMapper.deleteById(ordersId);
        // 判空返回
        return getResult(rows);
    }

    /**
     * 判空返回
     * @param rows 影响行数
     * @return 结果集
     */
    private static Result getResult(int rows) {
        // 是否保存成功
        if (rows == 0) {
            return Result.fail().message("失败");
        }
        return Result.ok().message("成功");
    }
}
