package com.wgw.firstlogin.controller;

import com.wgw.firstlogin.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guanwu
 * @created on 2022-10-31 16:21:48
 **/

@Controller
@RestController
public class LoginController {

    @GetMapping("/login-page")
    public String getLoginPage() {
        return "login";
    }

    //限制只能查询id < 10的用户
    @PreAuthorize("#id<10")
    @RequestMapping("/findById")
    public User findById(Integer id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    // 限制只能查询自己的信息
    @PreAuthorize("principal.username.equals(#username)")
    @RequestMapping("/findByName")
    public User findByName(String username) {
        User user = new User();
        user.setUsername(username);
        return user;
    }

    //限制只能新增用户名称为abc的用户
    @PreAuthorize("#user.username.equals('abc')")
    @RequestMapping("/add")
    public User add(User user) {
        return user;
    }

    @PostAuthorize("returnObject.id%2==0")
    @RequestMapping("/testPostAuth")
    public User find(int id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    @PostFilter("filterObject.id%2==0")
    @RequestMapping("/testPostFilter")
    public List<User> findAll() {
        List<User> userList = new ArrayList<User>();
        User user;
        for (int i=0; i<10; i++) {
            user = new User();
            user.setId(i);
            userList.add(user);
        }
        return userList;
    }

    @PreFilter(filterTarget="ids", value="filterObject%2==0")
    @RequestMapping("/testPreFilter")
    public void delete(@RequestParam("ids") ArrayList<Integer> ids,
                       @RequestParam("username") ArrayList<String> usernames) {
        System.out.println("ids:");
        for (Integer id : ids) {
            System.out.println(id);
        }


        for (String username : usernames) {
            System.out.println(username);
        }
    }
}
