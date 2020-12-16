package top.lzmvlog.authority.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.lzmvlog.authority.model.Authority;

import java.util.List;

/**
 * @author ShaoJie
 * @Date 2020年07月30 17:19
 * @Description: 用户权限接口
 */
public interface AuthorityService {

    /**
     * 保存用户权限
     *
     * @param authority 权限信息
     * @return
     */
    Integer save(Authority authority);

    /**
     * 查询用户的所有权限
     *
     * @param authorityPage 分页信息
     * @param authority     权限对象
     * @return list 用户所拥有的权限信息
     */
    IPage<Authority> selectPage(Page authorityPage, Authority authority);

    /**
     * 查询用户权限
     *
     * @param memberId 权限对象
     * @return map 权限map
     */
    List<Authority> selectList(String memberId);

    /**
     * 取消用户的权限
     *
     * @param id       权限id
     * @param memberId 用户id
     * @return
     */
    Integer deleteAuth(String id, String memberId);

    /**
     * 更新权限信息
     *
     * @param authority 权限对象
     */
    void update(Authority authority);
}
