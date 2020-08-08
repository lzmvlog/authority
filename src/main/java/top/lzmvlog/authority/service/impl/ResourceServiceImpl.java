package top.lzmvlog.authority.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lzmvlog.authority.dao.ResourceMapper;
import top.lzmvlog.authority.model.Resource;
import top.lzmvlog.authority.service.ResourceService;

/**
 * @author ShaoJie
 * @Date 2020年08月07 11:38
 * @Description: 文件业务逻辑
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceMapper resourceMapper;

    /**
     * 保存文件
     *
     * @param resource 文件信息
     * @return integer
     */
    @Override
    public Integer save(Resource resource) {
        return resourceMapper.insert(resource);
    }

    /**
     * 分页查询文件
     *
     * @param page     分页信息
     * @param resource 文件信息
     * @return list 分页后的文件集合
     */
    @Override
    public IPage<Resource> selectList(Page page, Resource resource) {
        return resourceMapper.selectPage(page, Wrappers.query(resource));
    }

    /**
     * 删除文件
     *
     * @param resource 文件信息
     * @return integer
     */
    @Override
    public Integer delete(Resource resource) {
        return resourceMapper.delete(Wrappers.query(resource));
    }

    /**
     * 更新文件
     *
     * @param resource 文件信息
     * @return integer
     */
    @Override
    public Integer update(Resource resource) {
        return resourceMapper.update(resource, Wrappers.update());
    }
}
