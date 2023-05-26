package cn.edu.mju.joygle.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * ClassName: RolesFilter
 * Package: cn.edu.mju.joygle.security
 * Description: 权限拦截器
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/24--14:51
 */
@Configuration
public class RolesFilter implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        for(ConfigAttribute configAttribute : configAttributes) {
            // 当前地址所需要得角色权限
            String urlRole = configAttribute.getAttribute();
            // 如果匿名可以访问就不用匹配角色
            if ("ROLE_anonymous".equals(urlRole)) {
                // 如果未登录提示登录
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new AccessDeniedException("尚未登录,请登录");
                } else {
                    return ;
                }
            }
            // 获得用户所授予得角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            // 判断是否满足url所需要的角色
            for (GrantedAuthority grantedAuthority : authorities) {
                if (grantedAuthority.getAuthority().equals(urlRole)) {
                    return ;
                }
            }
        }
        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
