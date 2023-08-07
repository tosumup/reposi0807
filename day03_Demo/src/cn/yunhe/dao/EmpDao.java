package cn.yunhe.dao;

import cn.yunhe.entity.Employees;

import java.util.List;

public interface EmpDao {

    public List<Employees> findAllEmployees();


    int del(int id);

    int insert(Employees employees);

    int updataEmpIo(Employees employees);

    List<Employees> findEmpByLimit(int cpage, int size);
}
