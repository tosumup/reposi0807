package cn.yunhe.servlet;


import cn.yunhe.entity.User;
import cn.yunhe.service.UserService;
import cn.yunhe.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    //day19_emp/user?method=login
    UserService userService= new UserServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if ("login".equals(method)) {
            login(req,resp);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //调用业务
        User user =userService.login(username,password);

        if (user!=null) {
            //登录成功
            HttpSession session = req.getSession();
            session.setAttribute("user",user);
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
        }else{
            //继续登录
            resp.sendRedirect(req.getContextPath()+"/emp/login.html");
        }

    }
}
