package cn.yunhe.dao.impl;

import cn.yunhe.dao.EmpDao;
import cn.yunhe.entity.Employees;
import cn.yunhe.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class EmpDaoImpl implements EmpDao {

    QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());

    @Override
    public List<Employees> findAllEmployees() {
        String sql = "select * from employees";
        try {
            return qr.query(sql, new BeanListHandler<Employees>(Employees.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public int del(int id) {
        String sql = "delete from employees where employeeId = ?";
        try {
            return qr.update(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insert(Employees employees) {
        String sql = "insert into employees values(null,?,?,?,?)";
        try {
            return qr.update(sql, employees.getName(), employees.getSalary(), employees.getHireDate(), employees.getDepartmentId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updataEmpIo(Employees employees) {
        String sql = "update employees set name=?,salary=?,hireDate=?,departmentId=? where employeeId=?";
        try {
            return qr.update(sql,employees.getName(),employees.getSalary(), employees.getHireDate(), employees.getDepartmentId(),employees.getEmployeeId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Employees> findEmpByLimit(int cpage, int size) {
        String sql = "select  * from employees limit ?,?";
        int start=(cpage-1)*size;
        try {
            return qr.query(sql,new BeanListHandler<Employees>(Employees.class),start,size);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
