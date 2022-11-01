package com.wgw.firstlogin.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author guanwu
 * @created 2022/10/29 21:49
 */

@Controller
@RequestMapping("/admin")
public class AdminController {


    @RequestMapping("/tomain")
    @ResponseBody
    public String tomain(){
        return "redirect:/main.html";
    }

    @RequestMapping("/demo")
    @ResponseBody
    public String demo(){
        return "demo: " + getUsername() + " 登录成功！！！";
    }

    @GetMapping("/index")
    @ResponseBody
    public String index() {
        String username = getUsername();
        return username + " 登录成功";
    }

    private String getUsername(){
        // 获取当前登录的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.isAuthenticated()){
            return null;
        }
        Object principal = authentication.getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username =((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
