package com.pzhu.bysj.config;

import com.pzhu.bysj.pojo.Admin;
import com.pzhu.bysj.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@SuppressWarnings("all")
public class LoginInterceptor implements HandlerInterceptor {

    //定义不拦截的请求 加入css image js是因为layui加载jsp页面的时候 会发送加载css这些的请求 同样被拦截了
    private static final String[] IGNORE_URI = {".css", ".gif", "image", ".js", "ico", "login"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession();

        String servletPath = request.getServletPath(); //获得请求的ServletPath
        for(String s : IGNORE_URI) {
            if(servletPath.contains(s)) { //包含字符串 并不是以s结尾
                return true;
            }
        }

        String requestURI = request.getRequestURI();


        //LoginInformation loginInformation = (LoginInformation) request.getSession().getAttribute(ConstantCommon.CURRENT_USER_KEY);
        User login = (User) session.getAttribute("login");
        Admin admin = (Admin) session.getAttribute("admin");

        if(requestURI.contains("/user_createImage")){
            return true;
        }

        if(login == null&&admin == null) {
            response.sendRedirect("/user/login.html"); //没有登录的用户自动跳转登录界面
            //redirect(request, response);
            return false;

        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
