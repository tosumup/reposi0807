package cn.yunhe.servlet;

import cn.yunhe.entity.Employees;
import cn.yunhe.entity.PageBean;
import cn.yunhe.service.EmpService;
import cn.yunhe.service.impl.EmpServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/emp")
public class EmpServlet extends HttpServlet {
    EmpService empService = new EmpServiceImpl();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");

        if ("findAll".equals(method)) {
            findAll(req, resp);
        } else if ("delete".equals(method)) {
            deleteAce(req, resp);
        } else if ("insert".equals(method)) {
            insert(req, resp);
        } else if ("updateEmp".equals(method)) {
            updateEmp(req, resp);
        }
    }

    private void updateEmp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String employeeId = req.getParameter("employeeId");
        String name = req.getParameter("name");
        String salary = req.getParameter("salary");
        String hireDate = req.getParameter("hireDate");
        String departmentId = req.getParameter("departmentId");
        Employees employees = new Employees(Integer.parseInt(employeeId), name, Double.parseDouble(salary), hireDate, Integer.parseInt(departmentId));
        int row = empService.updateEmp(employees);
        if (row > 0) {
            resp.getWriter().println(1);
        } else {
            resp.getWriter().println(0);
        }
    }

    private void deleteAce(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("employeeId"));
        int del = empService.delete(id);
        System.out.println(id);
        if (del > 0) {
            resp.getWriter().println(1);
        } else {
            resp.getWriter().println(0);
        }
    }


    //    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        //响应乱码
//        resp.setContentType("text/html;charset=utf-8");
//
//        //调用查询所有业务
//        List<Employees> list = empService.findAll();
//
//        //将集合转json
//        String json = mapper.writeValueAsString(list);
//
//        //响应
//        resp.getWriter().println(json);
//    }
    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //响应乱码
        resp.setContentType("text/html;charset=utf-8");
        //接收用户想看的页码
        String currentPage = req.getParameter("currentPage");
        //接收用户想每页显示几条
        String pageSize = req.getParameter("pageSize");
        //调用业务
        PageBean pb = empService.findPage(currentPage, pageSize);
        //将对象转json
        String s = mapper.writeValueAsString(pb);
        //将json数据响应回页面
        resp.getWriter().println(s);
    }


    //    添加数据
    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String salary = req.getParameter("salary");
        String hireDate = req.getParameter("hireDate");
        String departmentId = req.getParameter("departmentId");
        Employees emp = new Employees(name, Double.parseDouble(salary), hireDate, Integer.parseInt(departmentId));
        int ins = empService.insert(emp);
        if (ins > 0) {
            resp.getWriter().println(1);
        } else {
            resp.getWriter().println(0);
        }
    }
}
