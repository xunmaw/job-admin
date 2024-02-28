package com.xunmaw.graduate.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器执行");
        HttpSession session = request.getSession();
        if (session.getAttribute("student")==null&&session.getAttribute("enterprise")==null&&session.getAttribute("manager")==null){
            System.out.println("未登录");
            response.sendRedirect("index.jsp");
            return false;
        }
        else
            return true;
    }
}
