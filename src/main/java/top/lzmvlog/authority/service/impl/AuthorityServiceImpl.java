package top.lzmvlog.authority.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.lzmvlog.authority.dao.AuthorityMapper;
import top.lzmvlog.authority.model.Authority;
import top.lzmvlog.authority.service.AuthorityService;

import java.util.List;

/**
 * @author ShaoJie
 * @Date 2020年07月30 17:22
 * @Description: 用户权限业务逻辑
 */
@Service
@Slf4j
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityMapper authorityMapper;

    /**
     * 保存用户权限
     *
     * @param authority 权限信息
     * @return
     */
    @Override
    public Integer save(Authority authority) {
        return authorityMapper.insert(authority);
    }

    /**
     * 查询用户的所有权限
     *
     * @param page      分页信息
     * @param authority 权限对象
     * @return list 用户所拥有的权限信息
     */
    @Override
    public IPage<Authority> selectPage(Page page, Authority authority) {
        Page<Authority> authorityPage = authorityMapper.selectPage(page,
                Wrappers.<Authority>lambdaQuery()
                        .eq(StringUtils.isEmpty(authority.getId()), Authority::getId, authority.getId())
                        .eq(StringUtils.isEmpty(authority.getMemberId()), Authority::getMemberId, authority.getMemberId())
        );
        return authorityPage;
    }

    /**
     * 查询用户权限
     *
     * @param memberId 权限对象
     * @return map 权限map
     */
    @Override
    public List<Authority> selectList(String memberId) {
        return authorityMapper.selectList(Wrappers.<Authority>lambdaQuery().eq(Authority::getMemberId, memberId));
    }

    /**
     * 取消用户的权限
     *
     * @param id       权限id
     * @param memberId 用户id
     * @return
     */
    @Override
    public Integer deleteAuth(String id, String memberId) {
        return authorityMapper.delete(Wrappers.<Authority>lambdaQuery()
                .eq(Authority::getId, id).eq(Authority::getMemberId, memberId));
    }

    /**
     * 更新权限信息
     *
     * @param authority 权限对象
     */
    @Override
    public void update(Authority authority) {
        authorityMapper.updateById(authority);
    }

}
