package cn.service;

import cn.domain.PageBean;
import cn.domain.User;

import java.util.List;
import java.util.Map;

//用户管理的业务接口
public interface UserService {
    //查询所有用户信息
    public List<User> findAll();
    public User login(User user);
    void addUser(User user);
    void deletUser(String id);
    //根据id查询
    User findUserById(String id);
    //修改用户
    void updateUser(User user);
    //遍历删除用户
    void delSelectedUser(String[] uids);
    //分页查询
    PageBean<User> findUserByPages(String currentPage, String rows, Map<String, String[]> condition);
}
