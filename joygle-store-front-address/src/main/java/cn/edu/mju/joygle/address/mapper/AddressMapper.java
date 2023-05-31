package cn.edu.mju.joygle.address.mapper;

import cn.edu.mju.joygle.common.entity.pojo.StoreUserAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: AddressMapper
 * Package: cn.edu.mju.joygle.address.mapper
 * Description: 用户地址映射层
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/26--14:34
 */
@Mapper
public interface AddressMapper extends BaseMapper<StoreUserAddress> {

}
