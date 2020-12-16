package top.lzmvlog.authority.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ShaoJie
 * @Date 2020年06月04 13:08
 * @Description: 系统运行时出错
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceException extends RuntimeException {

    /**
     * 异常代码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String message;

    public ServiceException(String message) {
        this.message = message;
    }
}
