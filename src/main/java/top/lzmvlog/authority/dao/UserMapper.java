package top.lzmvlog.authority.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.lzmvlog.authority.model.User;

/**
 * @author ShaoJie
 * @Date 2020年05月12 10:19
 * @Description:
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectOneByName(QueryWrapper<User> query);
}