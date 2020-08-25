package top.lzmvlog.authority.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.lzmvlog.authority.model.Purview;

import java.util.List;
import java.util.Map;

/**
 * @author ShaoJie
 * @Date 2020年07月30 11:37
 * @Description: 权限接口
 */
public interface PurviewService {

    /**
     * 保存权限信息
     *
     * @param purview 权限对象
     * @return 是否保存成功
     */
    Integer insert(Purview purview);

    /**
     * 查询权限
     *
     * @param purviewPage 分页信息
     * @param purview     权限对象
     * @return 返回分页后的 list
     */
    IPage<Purview> selectList(Page purviewPage, Purview purview);

    /**
     * 查询权限
     *
     * @return 返回权限 list
     */
    List<Purview> selectList();

    /**
     * 查询权限
     *
     * @param id 用户id
     * @return map 返回权限 map
     */
    Map<String, Object> selectList(String id);

    /**
     * 删除权限
     *
     * @param purview 权限对象
     * @return
     */
    Integer deletePurview(Purview purview);

    /**
     * 修改权限信息
     *
     * @param purview 权限信息
     */
    void update(Purview purview);
}
