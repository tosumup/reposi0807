package cn.yunhe.service.impl;

import cn.yunhe.dao.EmpDao;
import cn.yunhe.dao.impl.EmpDaoImpl;
import cn.yunhe.entity.Employees;
import cn.yunhe.entity.PageBean;
import cn.yunhe.service.EmpService;

import java.util.List;

public class EmpServiceImpl implements EmpService {

    EmpDao empDao =new EmpDaoImpl();

    @Override
    public List<Employees> findAll() {
        return empDao.findAllEmployees();
    }

    @Override
    public int delete(int id) {
        return empDao.del(id);
    }

    @Override
    public int insert(Employees employees) {
        return empDao.insert(employees);
    }

    @Override
    public int updateEmp(Employees employees) {
        return empDao.updataEmpIo(employees);
    }

    @Override
    public PageBean findPage(String currentPage, String pageSize) {
        PageBean pb = new PageBean();
        int cpage = Integer.parseInt(currentPage);
        int size = Integer.parseInt(pageSize);
        //封装当前页
        pb.setCurrentPage(cpage);
        //封装每页显示条数
        pb.setPageSize(size);
        //获取用户想看的数据 根据 想看的页码和每页显示条数
        List<Employees> list =empDao.findEmpByLimit(cpage,size);

        //封装数据（将返回的查的集合放进去）
        pb.setList(list);

        //查询有多少条数据
        List<Employees> allEmployees = empDao.findAllEmployees();

        //封装总条数
        pb.setTotalCount(allEmployees.size());
        return pb;
    }


}
