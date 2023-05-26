package cn.edu.mju.joygle.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * ClassName: ServiceException
 * Package: cn.edu.mju.joygle.common.exception
 * Description: 业务异常
 * @EqualsAndHashCode(callSuper = true) 利用自身的属性和父类的属性生成HashCode
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/22--9:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * 错误提示
     */
    private String message;

    /**
     * 错误码
     */
    private Integer code;

    public ServiceException(String message){
        this.message = message;
    }
}
