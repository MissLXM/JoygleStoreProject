package cn.edu.mju.joygle.admin.service.impl;

import cn.edu.mju.joygle.admin.dto.UserDto;
import cn.edu.mju.joygle.admin.mapper.UserMapper;
import cn.edu.mju.joygle.admin.service.UserService;
import cn.edu.mju.joygle.common.core.domain.Result;
import cn.edu.mju.joygle.common.entity.pojo.StoreUser;
import cn.edu.mju.joygle.common.utils.StringUtils;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: UserServiceImpl
 * Package: cn.edu.mju.joygle.admin.service.impl
 * Description: 用户业务实现
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--19:18
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Setter(onMethod_ = @Autowired)
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 用户信息展示
     * @param currentPage 当前页码
     * @param pageSize    每页显示数
     * @param keyword     搜索关键字
     * @return 结果集
     */
    @Override
    public Result userInfoShow(Integer currentPage, Integer pageSize, String keyword) {
        // 初始化数据
        IPage<StoreUser> userIPage = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<StoreUser> wrapper = new LambdaQueryWrapper<>();

        // 是否进行搜索
        if ("null".equals(keyword)) { keyword = null; }
        if (StringUtils.isNotEmpty(keyword)) {
            wrapper.like(StoreUser::getNickname, keyword);
        }

        // 查询到的分页信息
        IPage<UserDto> userDtoPage = new Page<>(currentPage, pageSize);
        IPage<StoreUser> storeUserIPage = userMapper.selectPage(userIPage, wrapper);
        BeanUtil.copyProperties(storeUserIPage,userDtoPage);
        // 参数准备
        List<StoreUser> users = storeUserIPage.getRecords();
        List<UserDto> userDtoList = new ArrayList<>();
        // 封装结果集
        users.forEach(user -> {
            UserDto userDto = new UserDto();
            BeanUtil.copyProperties(user,userDto);
            userDtoList.add(userDto);
        });

        // 判空
        if (userDtoList.size() == 0) {
            return Result.fail().message("查询失败");
        }

        userDtoPage.setRecords(userDtoList);
        return Result.ok(userDtoPage).message("查询成功");
    }

    /**
     * 保存用户信息
     * @param user 用户信息
     * @return 结果集
     */
    @Override
    public Result userInfoSave(StoreUser user) {
        // 密码加密
        String newPwd = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(newPwd);
        // 保存用户信息
        int rows = userMapper.insert(user);
        // 判空返回
        return getResult(rows);
    }

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return 结果集
     */
    @Override
    public Result userInfoUpdate(StoreUser user) {
        // 根据ID查询用户信息
        StoreUser storeUser = userMapper.selectById(user.getUserId());
        if (storeUser == null) {
            return Result.fail().message("没有该用户");
        }
        // 获取数据库中和前端回传的密码
        String storeUserPassword = storeUser.getPassword();
        String userPassword = user.getPassword();

        // 判断是否相同
        if (!bCryptPasswordEncoder.matches(storeUserPassword, userPassword)) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        // 修改用户信息
        int rows = userMapper.updateById(user);
        // 判空返回
        return getResult(rows);
    }


    /**
     * 删除用户信息
     * @param userId 用户ID
     * @return 结果集
     */
    @Override
    public Result userInfoDelete(Integer userId) {
        // 根据ID删除
        int rows = userMapper.deleteById(userId);
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
