package org.sang.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private Boolean enabled;
    private Boolean locked;
    private List<Role> roles;

    /**
     * 获取当前用户对象所具有的的角色信息
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    /**
     * 获取当前用户对象的密码
     * @return
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 获取当前用户对象的用户名
     * @return
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 当前账户是否未过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 当前账户是否未锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    /**
     * 当前账户密码是否未过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 当前账户是否可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Boolean getEnabled() {
//        return enabled;
//    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", locked=" + locked +
                ", roles=" + roles +
                '}';
    }
}
