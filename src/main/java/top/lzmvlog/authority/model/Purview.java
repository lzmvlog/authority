package top.lzmvlog.authority.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @author ShaoJie
 * @Date 2020年07月30 11:02
 * @Description: 权限角色表
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@TableName(value = "user")
@NoArgsConstructor
public class Purview {

    /**
     * 用户id
     */
    @TableId("id")
    private String id;

    /**
     * 用户的名称
     */
    @TableField("authority")
    @NotNull(message = "权限不能为空")
    private String authority;

    /**
     * 用户密码
     */
    @TableField("role")
    @NotNull(message = "角色不能为空")
    private String role;

}
