package me.wtclmy.project.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther:chaoe
 * @date:2020/7/7
 **/


public class StudentInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(null==request.getSession().getAttribute("loginType")){
            response.sendRedirect("/login");
            return false;
        }
        else if ((null!=request.getSession().getAttribute("loginType"))&&(!request.getSession().getAttribute("loginType").equals("s"))){
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
