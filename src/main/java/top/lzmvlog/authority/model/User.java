package top.lzmvlog.authority.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author ShaoJie
 * @Date 2020年05月10 20:09
 * @Description: 用户的信息
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@TableName(value = "user")
public class User implements UserDetails, Serializable {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户的名称
     */
    private String name;

    /**
     * 用户密码
     */
    private String password;


    /**
     * 获取权限
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 获取密码
     *
     * @return
     */
    @Override
    public String getPassword() {
        return null;
    }

    /**
     * 获取用户名
     *
     * @return
     */
    @Override
    public String getUsername() {
        return null;
    }

    /**
     * 是否异常
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * 是否锁定
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * 凭证是否过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * 是否启用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return false;
    }
}
