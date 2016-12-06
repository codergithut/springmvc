package webSource.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Administrator on 2016/12/6.
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    //private MyUserDetailsService userService;

    /**
     * 自定义验证方式
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        //MyUserDetails user = (MyUserDetails) userService.loadUserByUsername(username);
//        if(user == null){
//            throw new BadCredentialsException("Username not found.");
//        }

        //加密过程在这里体现
//        if (!password.equals(user.getPassword())) {
//            throw new BadCredentialsException("Wrong password.");
//        }

//        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
//        return new UsernamePasswordAuthenticationToken(user, password, authorities);
        return null;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

}
