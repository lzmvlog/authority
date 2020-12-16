package top.lzmvlog.authority.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.lzmvlog.authority.dao.PurviewMapper;
import top.lzmvlog.authority.model.Authority;
import top.lzmvlog.authority.model.Purview;
import top.lzmvlog.authority.service.AuthorityService;
import top.lzmvlog.authority.service.PurviewService;

import java.util.HashMap;
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

    private static final String AUTH = "auth";

    private static final String ID = "id";

    /**
     * 保存权限信息
     *
     * @param purview 权限对象
     * @return 是否保存成功
     */
    @Override
    public Integer insert(Purview purview) {
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
    public IPage<Purview> selectList(Page purviewPage, Purview purview) {
        return purviewMapper.selectPage(purviewPage, Wrappers.<Purview>lambdaQuery()
                .eq(StringUtils.isEmpty(purview.getId()), Purview::getId, purview.getId())
                .like(StringUtils.isEmpty(purview.getRole()), Purview::getRole, purview.getRole())

        );
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
     * @param memberId 用户id
     * @return map 返回权限 map
     */
    @Override
    public Map<String, Object> selectList(String memberId) {
        List<Authority> authorities = authorityService.selectList(memberId);
        // 获取 List<String> id
        List<String> ids = authorities.stream().map(Authority::getPurviewId).collect(Collectors.toList());
        // 权限 map
        Map<String, Object> map = new HashMap<>();

        if (ids.size() != 0) {
            List<Purview> purviews = purviewMapper.selectBatchIds(ids);
            String auth = purviews.stream().map(Purview::getRole).collect(Collectors.joining(","));
            // 权限 String 的信息
            map.put(AUTH, auth);
        }
        map.put(ID, memberId);
        return map;
    }

    /**
     * 删除权限
     *
     * @param purviewId 权限对象
     * @return
     */
    @Override
    public Integer deletePurview(String purviewId) {
        return purviewMapper.delete(Wrappers.<Purview>lambdaQuery().eq(Purview::getId, purviewId));
    }

    /**
     * 修改权限信息
     *
     * @param purview 权限信息
     */
    @Override
    public void update(Purview purview) {
        purviewMapper.updateById(purview);
    }

}
