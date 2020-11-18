package cn.service.impl;

import cn.dao.UserDao;
import cn.dao.impl.UserDaoImpl;
import cn.domain.PageBean;
import cn.domain.User;
import cn.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public List<User> findAll() {
        //调用dao完成查询
        return userDao.findAll();
    }

    @Override
    public User login(User user) {
        return userDao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void addUser(User user) {
        userDao.add(user);
    }

    @Override
    public void deletUser(String user) {
        userDao.delet(Integer.parseInt(user));
    }

    @Override
    public User findUserById(String id) {
        return userDao.findById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        userDao.updata(user);
    }

    @Override
    public void delSelectedUser(String[] uids) {
        //遍历获取数据
        if (uids!=null&&uids.length>0)
      {
          for (String uid : uids) {
            userDao.delet(Integer.parseInt(uid));
        }
      }
    }

    @Override
    public PageBean<User> findUserByPages(String _currentPage, String _rows, Map<String, String[]> condition) {

        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        if (currentPage <= 0){
            currentPage = 1;
        }

        //创建空的pageBean对象
        PageBean<User> pb = new PageBean<User>();
        //设置参数

        pb.setRows(rows);
        //调用dao查询总记录数
        int totalCount = userDao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //调用dao查询list集合
        //计算开始记录
        int start = (currentPage - 1) * rows;
        List<User> list = userDao.findByPage(start,rows,condition);
        pb.setList(list);
        //计算总页码
        int total = totalCount % rows == 0 ? totalCount/rows:totalCount/rows+1;
        pb.setTotalPage(total);
        if (currentPage >= total){
            currentPage = total;
        }
        pb.setCurrentPage(currentPage);

        //返回pb
        return pb;
    }

}
