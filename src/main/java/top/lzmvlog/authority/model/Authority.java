package top.lzmvlog.authority.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author ShaoJie
 * @Date 2020年05月12 09:54
 * @Description: 角色权限
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@TableName(value = "authority")
@NoArgsConstructor
public class Authority {

    /**
     * 权限 id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 用户的 权限/角色
     */
    @TableField("authority")
    private String authority;

    /**
     * 用户id
     */
    @TableField("member_id")
    private String memberId;


}
