package cn.edu.mju.joygle.product.listener;

import cn.edu.mju.joygle.common.entity.param.orders.MuchProductNumberParam;

import cn.edu.mju.joygle.common.entity.pojo.StoreProduct;
import cn.edu.mju.joygle.product.mapper.ProductMapper;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName: ProductListener
 * Package: cn.edu.mju.joygle.product.listener
 * Description: 生成订单监听器
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/31--0:58
 */
@Component
public class ProductListener {

    @Setter(onMethod_ = @Autowired)
    private ProductMapper productMapper;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("sub.queue"),
            exchange = @Exchange("topic.product"),
            key = "sub.stock"
    ))
    public void productSubStock(List<MuchProductNumberParam> productNumberParams) {

        productNumberParams.forEach(product -> {
            // 获取商品信息
            StoreProduct storeProduct = productMapper.selectById(product.getProductId());
            // 修改库存
            storeProduct.setProductStock(storeProduct.getProductStock() - product.getProductNumber());
            productMapper.updateById(storeProduct);
});
    }
}
