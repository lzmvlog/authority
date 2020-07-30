package top.lzmvlog.authority.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.lzmvlog.authority.model.Purview;

/**
 * @author ShaoJie
 * @Date 2020年07月30 11:37
 * @Description:
 */
public interface PurviewService {

    /**
     * 保存权限信息
     *
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
    IPage<Purview> selectList(Page<Purview> objectPage, Purview purview);
}
