package cn.yunhe.dao;

import cn.yunhe.entity.User;

public interface UserDao {
    User findUserByUserNameAndPassword(String username, String password);
}
