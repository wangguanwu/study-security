package com.wgw.firstlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author guanwu
 * @created on 2022-10-31 16:21:48
 **/

@Controller
public class LoginController {

    @GetMapping("/login-page")
    public String getLoginPage() {
        return "login";
    }
}
