package top.lzmvlog.authority.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.lzmvlog.authority.model.User;
import top.lzmvlog.authority.model.vo.TokenVo;

/**
 * @author ShaoJie
 * @Date 2020年05月12 10:20
 * @Description: 用户接口
 */
public interface UserService {

    /**
     * 插入用户信息
     *
     * @param user 用户信息
     * @return
     */
    Integer insert(User user);

    /**
     * 查询用户信息
     *
     * @param user 用户信息
     * @return
     */
    TokenVo selectUser(User user);

    /**
     * 查询用户信息
     *
     * @param userPage 分页信息
     * @param user     用户名称
     * @return
     */
    IPage<User> selectPage(Page userPage, User user);

    /**
     * 查询用户信息
     *
     * @param account 用户账号
     * @return
     */
    User getUser(String account);

    /**
     * 禁用用户
     *
     * @param userId 用户信息
     */
    void disableUser(String userId);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     */
    void update(User user);

    /**
     * 查询用户自己的信息
     *
     * @param id
     * @return
     */
    User selectOne(String id);
}
