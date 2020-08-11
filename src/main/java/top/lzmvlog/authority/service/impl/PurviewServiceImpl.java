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
    public IPage<Purview> selectList(Page purviewPage, Purview purview) {
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
        List<Authority> authorities = authorityService.selectList(new Authority().setMemberId(id));
        // 获取 List<String> id
        List<String> ids = authorities.stream().map(Authority::getAuthority).collect(Collectors.toList());
        // 权限 map
//        Map<String, Object> map = purviews.stream().collect(Collectors.toMap(Purview::getAuthority, Purview::getRole, (a, b) -> b));
        Map<String, Object> map = new HashMap<>();
        String auth = "";
        if (ids.size() != 0) {
            List<Purview> purviews = purviewMapper.selectBatchIds(ids);
            for (Purview purview : purviews) {
                auth = purview.getRole() + ",";
            }
            // 权限 String 的信息
            map.put("auth", auth);
        }
        map.put("id", id);

        return map;
    }

    /**
     * 删除权限
     *
     * @param purview 权限对象
     * @return
     */
    @Override
    public Integer deletePurview(Purview purview) {
        return purviewMapper.delete(Wrappers.query(purview));
    }

    /**
     * 修改权限信息
     *
     * @param purview 权限信息
     */
    @Override
    public void updata(Purview purview) {
        purviewMapper.updateById(purview);
    }

}
