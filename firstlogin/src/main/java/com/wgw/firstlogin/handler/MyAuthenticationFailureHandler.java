package com.wgw.firstlogin.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author guanwu
 * @created 2022/10/30 11:48
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final String redirectUrl;

    public MyAuthenticationFailureHandler(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.sendRedirect(redirectUrl);
    }
}
