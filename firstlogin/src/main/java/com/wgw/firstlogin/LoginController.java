package com.wgw.firstlogin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guanwu
 * @created on 2022-10-26 20:13:48
 **/

@RequestMapping("/admin")
@RestController
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "hello security";
    }
}
