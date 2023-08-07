package cn.yunhe.filter;

import cn.yunhe.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//@WebFilter("/*")
public class FilterEnconding implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //解决post请求乱码
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");

        //判断路径是否需要拦截  不包括协议和端口的路径
        String uri = request.getRequestURI();

        ArrayList<String> list = new ArrayList<>();
        list.add("login.html");
        list.add("user");
        list.add("index.jsp");

        for (String url : list){
            if (uri.contains(url)){
                //不需要拦截的路径
                filterChain.doFilter(request,response);
                return;
            }
        }

        //需要拦截的路径
        User user = (User) request.getSession().getAttribute("user");

        if (user!=null) {
            //已经登录了
            //放行
            filterChain.doFilter(request,response);
        }else{
            //响应乱码
            response.setContentType("text/html;charset=utf-8");
            //5秒后跳转到登录页面
            response.getWriter().println("你没有权限操作,3秒钟跳转到登录页面！");
            response.setHeader("refresh","3;url=/day03_Demo/emp/login.html");
        }
    }

    @Override
    public void destroy() {

    }
}
