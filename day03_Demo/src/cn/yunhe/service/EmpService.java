package cn.yunhe.service;

import cn.yunhe.entity.Employees;
import cn.yunhe.entity.PageBean;

import java.util.List;

public interface EmpService {
    /*
    *  查询所有业务
    * */
    public List<Employees> findAll();
    //    删除员工信息
    public int delete(int id);
    //    添加员工
    public int insert(Employees employees);

    int updateEmp(Employees employees);

    PageBean findPage(String currentPage, String pageSize);
}
