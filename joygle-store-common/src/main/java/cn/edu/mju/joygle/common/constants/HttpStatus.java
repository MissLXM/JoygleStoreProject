package cn.edu.mju.joygle.common.constants;

/**
 * ClassName: HttpStatus
 * Package: cn.edu.mju.joygle.common.constants
 * Description: 状态码常量
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/22--9:32
 */
public class HttpStatus {

    /**
     * 操作成功
     */
    public static final int SUCCESS = 200;

    /**
     * 未授权
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * 访问受限，授权过期
     */
    public static final int FORBIDDEN = 403;

    /**
     * 系统内部错误
     */
    public static final int ERROR = 500;

}
