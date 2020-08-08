package top.lzmvlog.authority.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.lzmvlog.authority.model.Resource;

/**
 * @author ShaoJie
 * @Date 2020年08月07 11:38
 * @Description:
 */
public interface ResourceService {

    /**
     * 保存文件
     *
     * @param resource 文件信息
     * @return integer
     */
    Integer save(Resource resource);

    /**
     * 分页查询文件
     *
     * @param page     分页信息
     * @param resource 文件信息
     * @return list 分页后的文件集合
     */
    IPage<Resource> selectList(Page page, Resource resource);

    /**
     * 删除文件
     *
     * @param resource 文件信息
     * @return integer
     */
    Integer delete(Resource resource);

    /**
     * 更新文件
     *
     * @param resource 文件信息
     * @return integer
     */
    Integer update(Resource resource);

}
