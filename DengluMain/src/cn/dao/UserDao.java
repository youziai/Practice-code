package cn.dao;

import cn.JDBCUtil.JDBCUtil;
import cn.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {
    //声明JDBCTemplate对象共用
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDatasouce());

    public User login(User loginuser){
        try {
            //编写sql
            String sql = "select * from user where username = ? and password = ?";
            User user = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),loginuser.getUsername(),loginuser.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
