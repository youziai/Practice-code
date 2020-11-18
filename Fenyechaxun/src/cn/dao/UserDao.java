package cn.dao;

import cn.domain.User;

import java.util.List;
import java.util.Map;

//用户操作的dao
public interface UserDao {
    public List<User> findAll();
    public User findUserByUsernameAndPassword(String username, String password);
    void add(User user);
    void delet(int parseInt);

    User findById(int parseInt);

    void updata(User user);
    //查询总记录数字
    int findTotalCount(Map<String, String[]> condition);
    //分页查询每行记录
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}

