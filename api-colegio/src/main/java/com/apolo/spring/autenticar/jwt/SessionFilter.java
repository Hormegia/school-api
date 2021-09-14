package com.apolo.spring.autenticar.jwt;


import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class SessionFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        System.err.println(httpServletRequest.getSession());
        Cookie[] allCookies = httpServletRequest.getCookies();
        if (allCookies != null) {
            Cookie session = Arrays.stream(allCookies).filter(x -> x.getName().equals("SESSIONID")).findFirst().orElse(null);

            if (session != null) {
                session.setMaxAge(60);
                httpServletResponse.addCookie(session);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


}
