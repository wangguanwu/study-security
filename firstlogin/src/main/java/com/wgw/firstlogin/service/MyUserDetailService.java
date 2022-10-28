package com.wgw.firstlogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author guanwu
 * @created on 2022-10-27 19:18:54
 **/

//@Component
public class MyUserDetailService implements UserDetailsService {


    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String encryptPwd = passwordEncoder.encode("123456");

        return new User("guanwu", encryptPwd,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,user"));
    }
}
