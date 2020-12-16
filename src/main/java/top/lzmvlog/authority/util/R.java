package top.lzmvlog.authority.util;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ShaoJie
 * @Date 2020年06月03 11:13
 * @Description:
 */
@Data
@NoArgsConstructor
public class R {

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应数据
     */
    private Object data;

    /**
     * 响应成功应返回 响应码 - 响应信息 - 响应数据
     *
     * @param code 响应码
     */
    public R(int code) {
        this.code = code;
    }

    /**
     * 返回状态和数据
     *
     * @param code 响应信息
     * @param data 响应数据
     */
    public R(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }
}
