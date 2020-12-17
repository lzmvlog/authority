package top.lzmvlog.authority.model.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2020年12月17日 14:48
 * @Description:
 */
@Data
public class MenuTree {

    /**
     * 菜单id
     */
    private String id;

    /**
     * 父菜单名称
     */
    private String menuName;

    /**
     * 父级菜单id
     */
    private String parentId;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 子部门
     */
    private List<?> children = new ArrayList<>();
}
