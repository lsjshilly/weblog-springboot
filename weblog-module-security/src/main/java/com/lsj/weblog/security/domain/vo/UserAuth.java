package com.lsj.weblog.security.domain.vo;


import com.lsj.weblog.common.vo.RoleVo;
import com.lsj.weblog.common.vo.UserVo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class UserAuth implements UserDetails {
    /**
     * 用户实体类，用于表示系统中的用户信息。
     */

    private Long id; // 用户ID

    private String username; // 用户名

    private String password; // 密码

    private String nickname; // 昵称

    private String realname; // 真实姓名

    private String phone; // 电话号码

    private short sex; // 性别

    private List<RoleVo> roleVoList; // 用户角色列表

    private LocalDateTime createTime; // 用户创建时间

    public UserAuth(UserVo userVo) {
        this.id = userVo.getId();
        this.username = userVo.getUsername();
        this.password = userVo.getPassword();
        this.nickname = userVo.getNickname();
        this.realname = userVo.getRealname();
        this.phone = userVo.getPhone();
        this.sex = userVo.getSex();
        this.roleVoList = userVo.getRoleVoList();
        this.createTime = userVo.getCreateTime();
    }

    /**
     * 获取用户的权限集合。
     * <p>
     * 该方法将用户的角色转换为Spring Security所需的GrantedAuthority对象。
     * 如果用户没有角色，则返回一个空的权限列表。
     *
     * @return 用户的权限集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(this.roleVoList).orElse(new ArrayList<>()).stream().map(role -> (GrantedAuthority) () -> "ROLE_" + role.getName()).toList();
    }

    /**
     * 检查用户的账户是否过期。
     * <p>
     * 该方法始终返回true，表示用户的账户不过期。
     *
     * @return true，如果账户不过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 检查用户的账户是否被锁定。
     * <p>
     * 该方法始终返回true，表示用户的账户未被锁定。
     *
     * @return true，如果账户未被锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 检查用户的凭证（密码）是否过期。
     * <p>
     * 该方法始终返回true，表示用户的凭证不过期。
     *
     * @return true，如果凭证不过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 检查用户是否被启用。
     * <p>
     * 该方法始终返回true，表示用户已被启用。
     *
     * @return true，如果用户被启用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
