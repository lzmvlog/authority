package top.lzmvlog.authority.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lzmvlog.authority.dao.PurviewMapper;
import top.lzmvlog.authority.model.Purview;
import top.lzmvlog.authority.service.PurviewService;

/**
 * @author ShaoJie
 * @Date 2020年07月30 11:37
 * @Description:
 */
@Service
@Slf4j
public class PurviewServiceImpl implements PurviewService {

    @Autowired
    PurviewMapper purviewMapper;

    /**
     * 保存权限信息
     *
     * @param purview 权限对象
     * @return 是否保存成功
     */
    @Override
    public Integer insert(Purview purview) {
        purview.setId(IdUtil.fastSimpleUUID());
        return purviewMapper.insert(purview);
    }

    /**
     * 查询权限
     *
     * @param purviewPage 分页信息
     * @param purview     权限对象
     * @return 返回分页后的 list
     */
    @Override
    public IPage<Purview> selectList(Page<Purview> purviewPage, Purview purview) {
        return purviewMapper.selectPage(purviewPage, Wrappers.query(purview));
    }

}
