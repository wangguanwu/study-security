package com.wgw.firstlogin.service.impl;

import com.wgw.firstlogin.entity.Permission;
import com.wgw.firstlogin.entity.User;
import com.wgw.firstlogin.mapper.PermissionMapper;
import com.wgw.firstlogin.mapper.UserMapper;
import com.wgw.firstlogin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guanwu
 * @created on 2022-10-28 14:21:14
 **/

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final PermissionMapper permissionMapper;

    private final UserMapper userMapper;

    public UserServiceImpl(PermissionMapper permissionMapper, UserMapper userMapper) {
        this.permissionMapper = permissionMapper;
        this.userMapper = userMapper;
    }

    @Override
    public User getByUserName(String userName) {
        return userMapper.getUserByName(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("自定义登录逻辑");
        //从mysql查询用户
        User user = getByUserName(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(user!=null){
            List<Permission> permissions = permissionMapper.selectByUserId(user.getId());
            //设置权限
            permissions.forEach(permission -> {
                if (permission!=null && !StringUtils.isEmpty(permission.getEnname())){
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getEnname());
                    authorities.add(grantedAuthority);
                }
            });
            // 封装成UserDetails的实现类
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),user.getPassword(),authorities);
        }else {
            throw new UsernameNotFoundException("用户名不存在");
        }
    }
}
