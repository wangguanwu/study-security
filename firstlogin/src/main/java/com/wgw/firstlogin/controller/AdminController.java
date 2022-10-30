package com.wgw.firstlogin.controller;

import org.springframework.stereotype.Controller;
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

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "<h1>bidy</h1>";
    }
}
