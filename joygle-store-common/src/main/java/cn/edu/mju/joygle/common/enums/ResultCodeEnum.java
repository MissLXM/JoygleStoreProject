package cn.edu.mju.joygle.common.enums;

import cn.edu.mju.joygle.common.constants.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: ResultCodeEnum
 * Package: cn.edu.mju.joygle.common.result
 * Description: 状态码枚举
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/20--11:46
 */
@Getter
@AllArgsConstructor
public enum ResultCodeEnum {

    // 所有状态码信息
    SUCCESS(HttpStatus.SUCCESS,"成功"),
    FAIL(HttpStatus.ERROR,"失败");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 状态码信息
     */
    private final String message;

}
