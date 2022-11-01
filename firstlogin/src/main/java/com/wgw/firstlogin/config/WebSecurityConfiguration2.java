package com.wgw.firstlogin.config;

import com.wgw.firstlogin.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author guanwu
 * @created on 2022-10-31 19:21:39
 **/

@Configuration
public class WebSecurityConfiguration2 extends WebSecurityConfigurerAdapter {

    @Resource
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();

        http.authorizeRequests()
                .antMatchers("/admin/index").permitAll()
//                .antMatchers("/**/*.jpeg").permitAll()
//                .regexMatchers(".+[.]jp[e]*g").permitAll()
//                .mvcMatchers("/admin/index").servletPath("/web").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();


    }
}
