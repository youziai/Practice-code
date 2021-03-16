package com.itheima.service.impl;

import com.itheima.dao.RoleDao;
import com.itheima.dao.UserDao;
import com.itheima.domain.Role;
import com.itheima.domain.User;
import com.itheima.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    protected RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<User> list() {
        List<User> userList = userDao.findAll();
        //封装userlist中的每一个user的roles数据
        for (User user:userList){
            //获得user的id
            Long id = user.getId();
            //将id作为参数 查询当前userid对应的role集合数据
            List<Role> roles = roleDao.findRoleByUserId(id);
            user.setRoles(roles);
        }
        return userList;
    }

    @Override
    public void save(User user, Long[] roleIds) {
        //第一步 向sys_user表中存储数据
        Long userId = userDao.save(user);
        //第二步 向sys_user_role关系表中存储多条数据
        userDao.saveUserRoleRel(userId,roleIds);

    }

    @Override
    public void del(Long userId) {
        //1.删除关系图sys_user_role关系表
        userDao.delUserRoleRel(userId);
        //删除sys_user表
        userDao.del(userId);

    }
}
