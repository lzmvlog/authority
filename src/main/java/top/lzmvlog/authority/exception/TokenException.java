package top.lzmvlog.authority.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ShaoJie
 * @Date 2020年05月12 15:01
 * @Description: token 验证失败
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenException extends RuntimeException {

    /**
     * 异常代码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String message;

}
