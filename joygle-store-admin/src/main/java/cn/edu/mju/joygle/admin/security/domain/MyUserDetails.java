package cn.edu.mju.joygle.admin.security.domain;

import cn.edu.mju.joygle.common.entity.pojo.StoreAdmin;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * ClassName: MyUserDetails
 * Package: cn.edu.mju.joygle.admin.security.domain
 * Description: 自定义登录信息
 *
 * @Author:wjh
 * @Create:2023-06-2023/6/3--14:25
 */
@Data
@Accessors(chain = true)
public class MyUserDetails implements UserDetails {

    private StoreAdmin admin;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
