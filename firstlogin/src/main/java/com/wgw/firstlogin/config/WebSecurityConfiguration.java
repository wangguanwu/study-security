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
//                .successHandler(new MyAuthenticationSuccessHandler("/main.html"))
//                .successHandler(new MyAuthenticationSuccessHandler("/admin/index"))
//                .failureHandler(new MyAuthenticationFailureHandler("/error.html"))
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/main.html")
                .and().authorizeRequests()
                .antMatchers("/user/login", "/login.html", "/error.html", "/session/invalid").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

        http.sessionManagement()
                .invalidSessionUrl("/session/invalid")
                .maximumSessions(1)
                .expiredSessionStrategy(event -> {
                    HttpServletResponse response = event.getResponse();
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().println("你已被挤兑下线");
                });
//                .maxSessionsPreventsLogin(true);
    }
}
