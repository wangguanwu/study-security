package com.wgw.firstlogin.mapper;

import com.wgw.firstlogin.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author guanwu
 * @created on 2022-10-28 11:20:49
 **/

@Repository
public interface UserMapper {

    @Select("select * from tb_user where username=#{username}")
    User getUserByName(String userName);
}
