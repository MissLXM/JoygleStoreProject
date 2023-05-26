package cn.edu.mju.joygle.address.service;

import cn.edu.mju.joygle.common.core.domain.Result;

/**
 * ClassName: AddressService
 * Package: cn.edu.mju.joygle.address.service
 * Description: 地址业务接口
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/26--14:11
 */
public interface AddressService {

    /**
     * 展示用户的所有地址信息
     * @param userId 用户ID
     * @return 用户地址信息
     */
    Result showUserAddress(Integer userId);
}
