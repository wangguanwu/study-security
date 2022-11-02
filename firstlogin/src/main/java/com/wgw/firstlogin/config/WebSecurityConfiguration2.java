package com.wgw.firstlogin.config;

import com.wgw.firstlogin.handler.MyAccessDeniedHandler;
import com.wgw.firstlogin.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @author guanwu
 * @created on 2022-10-31 19:21:39
 **/

@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true, securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfiguration2 extends WebSecurityConfigurerAdapter {

    @Resource
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("fox")
                .password(passwordEncoder().encode("123456"))
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("admin,user,ROLE_admin"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();

        http.authorizeRequests()
//                .antMatchers("/admin/index").hasAuthority("admin")
//                .antMatchers("/admin/demo").hasAuthority("user")
//                .antMatchers("/**/*.jpeg").permitAll()
//                .regexMatchers(".+[.]jp[e]*g").permitAll()
//                .mvcMatchers("/admin/index").servletPath("/web").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

        http.exceptionHandling()
                .accessDeniedHandler(new MyAccessDeniedHandler());


    }
}
