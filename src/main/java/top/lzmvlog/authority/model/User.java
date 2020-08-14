package top.lzmvlog.authority.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
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
@NoArgsConstructor
public class User implements Serializable, UserDetails {

    /**
     * 用户id
     */
    @TableId("id")
    private String id;

    /**
     * 用户的名称
     */
    @TableField("name")
    @NotNull(message = "用户名称不能为空")
    private String name;

    /**
     * 用户的账号
     */
    @TableField("account")
    @NotNull(message = "账号不能为空")
    private String account;

    /**
     * 用户密码
     */
    @TableField("password")
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 用户头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 是否启用账号
     * <p>
     * condition = "%s = true" 是 @TableField 注解的其中一个属性
     * 可以给当前的的查询字段加上一个字段 例如：isEnable = true 的默认条件值
     */
//    @TableField(value = "isEnable", fill = FieldFill.INSERT_UPDATE, update = "true", condition = "%s = true")
    @TableField(value = "isEnable")
    private Boolean enable;

    /**
     * Returns the authorities granted to the user. Cannot return <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * Returns the username used to authenticate the user. Cannot return <code>null</code>.
     *
     * @return the username (never <code>null</code>)
     */
    @Override
    public String getUsername() {
        return null;
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
     */
    @Override
    public boolean isEnabled() {
        return false;
    }
}
