package cn.edu.mju.joygle.orders.service.impl;

import cn.edu.mju.joygle.common.client.address.AddressClient;
import cn.edu.mju.joygle.common.client.product.ProductClient;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.dto.orders.OrderDto;
import cn.edu.mju.joygle.common.entity.dto.orders.OrdersDto;
import cn.edu.mju.joygle.common.entity.dto.orders.OrdersInfoDto;
import cn.edu.mju.joygle.common.entity.dto.product.ProductDto;
import cn.edu.mju.joygle.common.entity.param.orders.MuchProductNumberParam;
import cn.edu.mju.joygle.common.entity.param.orders.MuchProductParam;
import cn.edu.mju.joygle.common.entity.pojo.StoreUserOrders;
import cn.edu.mju.joygle.orders.exception.OrdersException;
import cn.edu.mju.joygle.orders.mapper.OrdersMapper;
import cn.edu.mju.joygle.orders.service.OrdersService;
import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: OrdersServiceImpl
 * Package: cn.edu.mju.joygle.orders.service.impl
 * Description: 订单业务实现
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/30--23:35
 */
@Slf4j
@Service
public class OrdersServiceImpl implements OrdersService {

    @Setter(onMethod_ = @Autowired)
    private ProductClient productClient;

    @Setter(onMethod_ = @Autowired)
    private AddressClient addressClient;

    @Setter(onMethod_ = @Autowired)
    private OrdersMapper ordersMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 订单生成后展示
     * @param userId 用户ID
     * @return 结果集
     */
    @Override
    public Result ordersShow(String authorization,Integer userId) {

        // 参数准备
        List<OrdersInfoDto> ordersInfoDtoList = new ArrayList<>();

        // 根据用户ID查询所有订单
        List<StoreUserOrders> userOrders = ordersMapper.selectList(
                new LambdaQueryWrapper<StoreUserOrders>()
                        .eq(StoreUserOrders::getUserId, userId)
        );

        // 利用流根据 orderId 分组
        Map<Long, List<StoreUserOrders>> orders =
                userOrders.stream().collect(Collectors.groupingBy(StoreUserOrders::getOrderId));

        for (List<StoreUserOrders> storeUserOrders : orders.values()) {
            // 参数准备
            double ordersTotalPrice = 0.0;
            List<OrderDto> orderDtos = new ArrayList<>();
            OrdersInfoDto ordersInfoDto = new OrdersInfoDto();

            // 获取该订单的订单号
            log.info("订单号: " + storeUserOrders.get(0).getOrderId());
            Long orderId = storeUserOrders.get(0).getOrderId();

            // 查询订单号相同的记录
            List<StoreUserOrders> userOrdersList = ordersMapper.selectList(
                    new LambdaQueryWrapper<StoreUserOrders>()
                            .eq(StoreUserOrders::getOrderId, orderId)
            );

            for(StoreUserOrders storeUserOrder : userOrdersList) {

                // 查询地址
                Integer addressId = storeUserOrder.getAddressId();
                Object object = addressClient.showUserAddressByAddressId(authorization, addressId).getData();

                // JSON转换
                String jsonString = JSON.toJSONString(object);
                String address = JSON.parseObject(jsonString, String.class);

                // 查询商品信息
                Integer productId = storeUserOrder.getProductId();
                object = productClient.productInfoShow(authorization, productId).getData();

                // JSON转换
                jsonString = JSON.toJSONString(object);
                ProductDto productDto = JSON.parseObject(jsonString, ProductDto.class);

                // 拷贝
                OrderDto orderDto = new OrderDto();
                BeanUtil.copyProperties(productDto,orderDto);

                // 商品对象
                orderDto
                        .setProductNumber(storeUserOrder.getProductNumber())
                        .setProductTotalPrice(
                                productDto.getProductSalePrice() * storeUserOrder.getProductNumber()
                        );

                // 结果集封装(同订单的商品信息)
                orderDtos.add(orderDto);

                // 同订单对象
                ordersInfoDto
                        .setOrderId(orderId)
                        .setAddress(address)
                        .setOrderDtos(orderDtos);

                for (OrderDto order : orderDtos) {
                    ordersTotalPrice = ordersTotalPrice + order.getProductTotalPrice();
                }
                ordersInfoDto.setOrderTotalPrice(ordersTotalPrice);
            }
            // 所有订单对象
            ordersInfoDtoList.add(ordersInfoDto);
        }

        // 封装最后结果集
        return Result.ok(ordersInfoDtoList).message("展示成功");
    }

    /**
     * 订单生成前展示
     * @param authorization    认证信息
     * @param userId           用户ID
     * @param muchProductParam 商品数量和商品信息
     * @return 结果集
     */
    @Override
    public Result orderShowPrice(String authorization, Integer userId, MuchProductParam muchProductParam) {
        // 参数准备
        double productTotalPrice = 0.0;
        List<OrderDto> orderDtos = new ArrayList<>();
        List<MuchProductNumberParam> products = muchProductParam.getProducts();

        // 判断参数是否有
        if (products.size() == 0) {
            return Result.fail().message("未选中商品");
        }

        products.forEach(product -> {
            // 获取商品ID
            Integer productId = product.getProductId();

            // 查询商品信息
            Object object = productClient.productInfoShow(authorization, productId).getData();

            // JSON转换
            String jsonString = JSON.toJSONString(object);
            ProductDto productDto = JSON.parseObject(jsonString, ProductDto.class);

            if (productDto == null) {
                throw new OrdersException("未查询到商品");
            }

            // 拷贝
            OrderDto orderDto = new OrderDto();
            BeanUtil.copyProperties(productDto, orderDto);

            // 设置数量和总价格
            orderDto
                    .setProductNumber(product.getProductNumber())
                    .setProductTotalPrice(
                            orderDto.getProductSalePrice() * product.getProductNumber()
                    );

            // 添加
            orderDtos.add(orderDto);
        });

        // 遍历 orderDtos 算取所有商品总价格
        for (OrderDto orderDto : orderDtos) {
            productTotalPrice = productTotalPrice + orderDto.getProductTotalPrice();
        }

        // 封装结果集
        OrdersDto ordersDto =
                new OrdersDto()
                        .setOrderDtos(orderDtos)
                        .setOrderTotalPrice(productTotalPrice);

        // 判空
        if (ordersDto == null) {
            return Result.fail().message("订单信息显示失败");
        }

        return Result.ok(ordersDto).message("订单信息展示成功");
    }

    /**
     * 商品订单生成
     *
     * @param authorization    认证信息
     * @param userId           用户ID
     * @param muchProductParam 商品参数
     * @return 结果集
     */
    @Override
    @Transactional
    public Result muchProductOrderSave(String authorization, Integer userId, MuchProductParam muchProductParam) {
        // 参数准备
        Long orderId = System.currentTimeMillis();
        Integer addressId = muchProductParam.getAddressId();
        List<MuchProductNumberParam> products = muchProductParam.getProducts();

        // 判断参数是否有
        if (products.size() == 0) {
            return Result.fail().message("未选中商品");
        }

        // 计算单商品的价格 = 购买数量 * 实际销售量

        products.forEach(product -> {
            Integer productId = product.getProductId();
            // 获取商品信息
            Object object = productClient.productInfoShow(authorization, productId).getData();

            // JSON转换
            String jsonString = JSON.toJSONString(object);
            ProductDto productDto = JSON.parseObject(jsonString, ProductDto.class);

            // 该商品的总价格
            Double totalPrice = productDto.getProductSalePrice() * product.getProductNumber();

            // 生成订单
            StoreUserOrders userOrders =
                        new StoreUserOrders()
                            .setOrderId(orderId)
                            .setUserId(userId)
                            .setAddressId(addressId)
                            .setProductId(productId)
                            .setProductNumber(product.getProductNumber())
                            .setProductPrice(totalPrice);

            int rows = ordersMapper.insert(userOrders);

            if (rows == 0) {
                throw new OrdersException("订单创建失败");
            }
        });

        // RabbitMQ发送消息给商品服务(库存量 - productNumber)
        rabbitTemplate.convertAndSend("topic.product","sub.stock" , products);

        return Result.ok().message("订单创建成功");
    }

}
