package cn.yunhe.dao.impl;

import cn.yunhe.dao.UserDao;
import cn.yunhe.entity.User;
import cn.yunhe.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    QueryRunner qr=  new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public User findUserByUserNameAndPassword(String username, String password) {
        String sql = "select * from user where username =? and password =?";
        try {
            return qr.query(sql,new BeanHandler<User>(User.class),username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
