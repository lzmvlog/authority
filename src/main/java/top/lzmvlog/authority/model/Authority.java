package top.lzmvlog.authority.model;

import com.baomidou.mybatisplus.annotation.IdType;
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
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 用户的 权限/角色
     */
    private String purviewId;

    /**
     * 用户id
     */
    private String memberId;

    /**
     * 角色名称
     */
    private String role;


}
