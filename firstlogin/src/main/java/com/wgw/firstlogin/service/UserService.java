package com.wgw.firstlogin.service;

import com.wgw.firstlogin.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author guanwu
 * @created on 2022-10-28 14:19:43
 **/

public interface UserService extends UserDetailsService {

    User getByUserName(String userName);
}
