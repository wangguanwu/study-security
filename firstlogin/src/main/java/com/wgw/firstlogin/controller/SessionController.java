package com.wgw.firstlogin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author guanwu
 * @created 2022/10/29 21:49
 */

@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String sessionInvalid() {
        return "session失效";
    }
}
