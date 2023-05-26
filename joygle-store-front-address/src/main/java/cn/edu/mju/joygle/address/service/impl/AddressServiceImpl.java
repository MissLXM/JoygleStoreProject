package cn.edu.mju.joygle.address.service.impl;

import cn.edu.mju.joygle.address.mapper.AddressMapper;
import cn.edu.mju.joygle.address.service.AddressService;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.StoreUserAddress;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: AddressServiceImpl
 * Package: cn.edu.mju.joygle.address.service
 * Description: 地址业务接口实现
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/26--14:12
 */
@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

    @Setter(onMethod_ = @Autowired)
    private AddressMapper addressMapper;

    /**
     * 展示用户的所有地址信息
     * @param userId 用户ID
     * @return 用户地址信息
     */
    @Override
    public Result showUserAddress(Integer userId) {
        // 条件构造器
        LambdaQueryWrapper<StoreUserAddress> wrapper = new LambdaQueryWrapper<>();
        // 设置条件
        wrapper
                .eq(StoreUserAddress::getUserId, userId);
        List<StoreUserAddress> storeUserAddresses = addressMapper.selectList(wrapper);
        // 打印信息
        log.info(storeUserAddresses.toString());
        return Result.ok(storeUserAddresses).message("查询成功");
    }
}
