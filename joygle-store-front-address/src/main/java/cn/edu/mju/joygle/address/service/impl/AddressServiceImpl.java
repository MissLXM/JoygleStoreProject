package cn.edu.mju.joygle.address.service.impl;

import cn.edu.mju.joygle.common.entity.dto.address.AddressDto;
import cn.edu.mju.joygle.common.entity.dto.address.UserAddressDto;
import cn.edu.mju.joygle.address.mapper.AddressMapper;
import cn.edu.mju.joygle.address.service.AddressService;
import cn.edu.mju.joygle.common.core.constants.HttpStatus;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.pojo.StoreUserAddress;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
     * 根据地址ID查询地址信息
     * @param addressId 地址ID
     * @return 结果集
     */
    @Override
    public Result showUserAddressByAddressId(Integer addressId) {
        // 查询地址信息
        StoreUserAddress storeUserAddress = addressMapper.selectById(addressId);

        // 判空
        if (storeUserAddress == null) {
            return Result.fail().message("查无此地址");
        }

        return Result.ok(storeUserAddress.getAddress()).message("查询地址成功");
    }

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
        // 判空
        if (storeUserAddresses.size() == 0) {
            return Result.fail().message("暂无地址信息").code(HttpStatus.NO_FOUND);
        }
        // 封装回显数据中的地址集合
        List<AddressDto> addressDtos = new ArrayList<>();
        storeUserAddresses.forEach(userAddress -> {
            // 初始化地址回显
            AddressDto addressDto = new AddressDto();
            // 利用 hutool 工具包的深拷贝,拷贝对象
            BeanUtil.copyProperties(userAddress,addressDto);
            log.info(addressDto.toString());
            // 添加到用户地址回显中
            addressDtos.add(addressDto);
        });
        // 封装回显结果集
        UserAddressDto userAddressDto = new UserAddressDto();
        userAddressDto.setUserId(userId).setAddressList(addressDtos);
        return Result.ok(userAddressDto).message("查询成功");
    }

    /**
     * 保存用户地址信息
     * @param userAddress 用户地址信息
     * @return 结果集
     */
    @Override
    public Result saveUserAddress(StoreUserAddress userAddress) {
        // 调用保存地址
        int rows = addressMapper.insert(userAddress);
        // 判断是否保存成功
        if (rows != 0) {
            return Result.ok().message("地址保存成功");
        }
        return Result.fail().message("保存地址失败");
    }

    /**
     * 修改用户地址信息
     * @param userAddress 用户地址信息
     * @return 结果集
     */
    @Override
    public Result updateUserAddress(StoreUserAddress userAddress) {
        // 调用修改地址
        int rows = addressMapper.updateById(userAddress);
        // 判断是否保存成功
        if (rows != 0) {
            return Result.ok().message("地址修改成功");
        }
        return Result.fail().message("地址修改失败");
    }

    /**
     * 删除用户地址信息
     * @param addressId 地址ID
     * @return 结果集
     */
    @Override
    public Result deleteUserAddress(Integer addressId) {
        int rows = addressMapper.deleteById(addressId);
        if (rows != 0) {
            return Result.ok().message("地址删除成功");
        }
        return Result.fail().message("地址删除失败");
    }
}
