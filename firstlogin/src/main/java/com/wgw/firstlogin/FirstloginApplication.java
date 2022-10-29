package com.wgw.firstlogin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wgw.firstlogin.mapper")
public class FirstloginApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstloginApplication.class, args);
    }

}
