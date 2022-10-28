package com.wgw.firstlogin.config;

import com.wgw.firstlogin.service.MyUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author guanwu
 * @created on 2022-10-27 19:33:35
 **/

@Configuration
public class WebSecurityConfiguration    {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String password = passwordEncoder().encode("123456");
        auth.inMemoryAuthentication()
                .withUser("guanwu")
                .password(password)
                .roles("admin")
                .and()
                .withUser("fox")
                .password(password)
                .roles("user");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        String pwd = passwordEncoder().encode("123");
        AuthenticationManagerBuilder authenticationManagerBuilder = new AuthenticationManagerBuilder();
        httpSecurity.authenticationManager(Au)
        return httpSecurity.authenticationManager(manager).
                build();

    }
}
