package top.lzmvlog.authority.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lzmvlog.authority.dao.PurviewMapper;
import top.lzmvlog.authority.model.Authority;
import top.lzmvlog.authority.model.Purview;
import top.lzmvlog.authority.service.AuthorityService;
import top.lzmvlog.authority.service.PurviewService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ShaoJie
 * @Date 2020年07月30 11:37
 * @Description: 权限业务逻辑
 */
@Service
@Slf4j
public class PurviewServiceImpl implements PurviewService {

    @Autowired
    PurviewMapper purviewMapper;

    @Autowired
    AuthorityService authorityService;

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

    /**
     * 查询权限
     *
     * @return 返回权限 list
     */
    @Override
    public List<Purview> selectList() {
        return purviewMapper.selectList(Wrappers.query());
    }

    /**
     * 查询权限
     *
     * @param id 用户id
     * @return map 返回权限 map
     */
    @Override
    public Map<String, Object> selectList(String id) {
        // 获取 List<String> id
        List<String> ids = authorityService.selectList(new Authority().setMemberId(id)).stream().map(Authority::getId).collect(Collectors.toList());
        List<Purview> purviews = purviewMapper.selectBatchIds(ids);
        // 权限 map
        Map<String, Object> map = purviews.stream().collect(Collectors.toMap(Purview::getAuthority, Purview::getRole, (a, b) -> b));
        return map;
    }

}
