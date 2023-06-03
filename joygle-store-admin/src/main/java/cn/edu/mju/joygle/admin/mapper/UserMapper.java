package cn.edu.mju.joygle.admin.mapper;

import cn.edu.mju.joygle.common.entity.pojo.StoreUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: UserMapper
 * Package: cn.edu.mju.joygle.admin.mapper
 * Description: 用户映射
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--19:17
 */
@Mapper
public interface UserMapper extends BaseMapper<StoreUser> {

}
