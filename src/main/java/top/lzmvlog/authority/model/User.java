package top.lzmvlog.authority.model;

import com.baomidou.mybatisplus.annotation.IdType;
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
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 用户的名称
     */
    @NotNull(message = "用户名称不能为空")
    private String name;

    /**
     * 用户的账号
     */
    @NotNull(message = "账号不能为空")
    private String account;

    /**
     * 用户密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 是否启用账号
     * <p>
     * condition = "%s = true" 是 @TableField 注解的其中一个属性
     * 可以给当前的的查询字段加上一个字段 例如：isEnable = true 的默认条件值
     */
//    @TableField(value = "isEnable", fill = FieldFill.INSERT_UPDATE, update = "true", condition = "%s = true")
    @TableField(value = "isEnable")
    private Boolean enable;

}
