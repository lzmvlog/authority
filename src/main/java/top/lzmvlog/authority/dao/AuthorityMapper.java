package top.lzmvlog.authority.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.lzmvlog.authority.model.Authority;

/**
 * @author ShaoJie
 * @Date 2020年06月07 22:44
 * @Description: 权限接口
 */
@Mapper
public interface AuthorityMapper extends BaseMapper<Authority> {
}
