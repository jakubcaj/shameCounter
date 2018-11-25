package com.idc.sterba.demo.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.NestedServletException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessDeniedFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {

            if (e instanceof NestedServletException &&
                    ((NestedServletException) e).getRootCause() instanceof AccessDeniedException) {

                HttpServletRequest rq = (HttpServletRequest) servletRequest;
                HttpServletResponse rs = (HttpServletResponse) servletResponse;

                    rs.sendError(HttpStatus.UNAUTHORIZED.value());

            }
        }
    }
}
