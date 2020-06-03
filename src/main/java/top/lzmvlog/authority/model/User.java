package top.lzmvlog.authority.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author ShaoJie
 * @Date 2020年05月10 20:09
 * @Description: 用户的信息
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@TableName(value = "user")
@NoArgsConstructor
public class User implements Serializable {

    /**
     * 用户id
     */
    @TableId("id")
    private String id;

    /**
     * 用户的名称
     */
    @TableField("name")
    @NotNull(message = "账号不能为空")
    private String name;

    /**
     * 用户密码
     */
    @TableField("password")
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 是否启用账号
     */
    @TableField("enable")
    private boolean enable;

}
