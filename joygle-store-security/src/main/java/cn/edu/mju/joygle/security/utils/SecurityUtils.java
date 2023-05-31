package cn.edu.mju.joygle.security.utils;

//import cn.edu.mju.joygle.common.constants.HttpStatus;
//import cn.edu.mju.joygle.common.entity.pojo.StoreUser;
//import cn.edu.mju.joygle.common.exception.ServiceException;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
///**
// * ClassName: SecurityUtils
// * Package: cn.edu.mju.joygle.common.utils
// * Description: 安全框架工具类
// *
// * @Author:wjh
// * @Create:2023-05-2023/5/22--9:17
// */
//public class SecurityUtils {
//
//    /**
//     * 用户ID
//     * @return 用户ID
//     */
//    public static Integer getUserId() {
//        try {
//            return getLoginUser().getUserId();
//        }
//        catch (Exception e) {
//             throw new ServiceException("获取用户ID异常", HttpStatus.UNAUTHORIZED);
//        }
//    }
//
//
//    /**
//     * 获取用户登录名称
//     * @return 用户登录名称
//     */
//    public static String getUsername() {
//        try {
//            return getLoginUser().getUsername();
//        }
//        catch (Exception e) {
//             throw new ServiceException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
//        }
//    }
//
//    /**
//     * 获取用户
//     * @return 用户
//     */
//    public static StoreUser getLoginUser() {
//        try {
//            return (StoreUser) getAuthentication().getPrincipal();
//        }
//        catch (Exception e) {
//             throw new ServiceException("获取用户信息异常", HttpStatus.UNAUTHORIZED);
//        }
//    }
//
//    /**
//     * Authentication
//     * @return 根据Security上下文获取登录信息
//     */
//    public static Authentication getAuthentication() {
//        return SecurityContextHolder.getContext().getAuthentication();
//    }
//
//    /**
//     * 生成 BCryptPasswordEncoder 加密密码
//     * @param password 加密前的密码
//     * @return 加密后的密码
//     */
//    public static String encryptPassword(String password) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        return passwordEncoder.encode(password);
//    }
//
//    /**
//     * 判断密码是否相同
//     * @param rawPassword 真实密码
//     * @param encodedPassword 加密后字符
//     * @return 结果
//     */
//    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        return passwordEncoder.matches(rawPassword, encodedPassword);
//    }
//}
