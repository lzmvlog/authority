package top.lzmvlog.authority.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        authority.setId(IdUtil.fastSimpleUUID());
        return authorityMapper.insert(authority);
    }

    /**
     * 查询用户的所有权限
     *
     * @param authorityPage 分页信息
     * @param authority     权限对象
     * @return list 用户所拥有的权限信息
     */
    @Override
    public IPage<Authority> selectListByMemberId(Page<Authority> authorityPage, Authority authority) {
        Page<Authority> memberId = authorityMapper.selectPage(authorityPage, Wrappers.query(authority).eq("memberId", authority.getMemberId()));
        return memberId;
    }

    /**
     * 查询用户权限
     *
     * @param authority 权限对象
     * @return map 权限map
     */
    @Override
    public List<Authority> selectList(Authority authority) {
        return authorityMapper.selectList(Wrappers.query(new Authority().setMemberId(authority.getMemberId())));
    }

}