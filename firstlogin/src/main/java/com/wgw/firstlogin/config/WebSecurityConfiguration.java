package com.wgw.firstlogin.config;

import com.wgw.firstlogin.handler.MyAuthenticationFailureHandler;
import com.wgw.firstlogin.handler.MyAuthenticationSuccessHandler;
import com.wgw.firstlogin.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;


/**
 * @author guanwu
 * @created on 2022-10-27 19:33:35
 **/

@Configuration
public class WebSecurityConfiguration  extends WebSecurityConfigurerAdapter  {

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
        http.formLogin()
                .loginPage("/login.html")
                .successHandler(new MyAuthenticationSuccessHandler("/main.html"))
                .failureHandler(new MyAuthenticationFailureHandler("/error.html"))
                .loginProcessingUrl("/user/login")
//                .defaultSuccessUrl("/admin/index")
                .and().authorizeRequests()
                .antMatchers("/user/login", "/login.html", "/error.html").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

    }
}
