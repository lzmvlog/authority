package top.lzmvlog.authority.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author ShaoJie
 * @Date 2020年05月12 09:54
 * @Description: 角色权限
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Authority {

    /**
     * 权限 id
     */
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private Integer id;

    /**
     * 用户id
     */
    private Integer memberId;

    /**
     * 用户的 权限/角色
     */
    private String roles;
}
