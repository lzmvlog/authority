package top.lzmvlog.authority.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2020年12月17日 11:39
 * @Description:
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@TableName(value = "menu")
@NoArgsConstructor
public class Menu {

    /**
     * 菜单id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单地址
     */
    private String path;

    /**
     * 父级菜单id
     */
    private String parentId;

    /**
     * 菜单是否显示
     */
    private Integer visible;

    /**
     * 备注
     */
    private String remark;

    /**
     * 角色
     */
    private String role;

}
