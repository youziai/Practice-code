package cn.dao.impl;

import cn.dao.UserDao;
import cn.domain.User;
import cn.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        //使用jdbc操作数据库
        //定义sql
        String sql = "select * from user";
        List<User> query = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return query;
    }


    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        try{
        String sql = "select * from user where username = ? and password = ?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        return user;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void add(User user) {
        String sql = "insert into user values(null,?,?,?,?,?,?,null,null)";
        //仅添加有问号的个数 要与数据库名一样
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());

    }

    @Override
    public void delet(int parseInt) {
        String sql = "delete from user where id = ?";
        template.update(sql,parseInt);

    }

    @Override
    public User findById(int parseInt) {
        String sql = "select * from user where id = ?";
       return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),parseInt);
    }

    @Override
    public void updata(User user) {
        String sql = "update user set name = ?,gender = ?,age = ?,address = ?,qq = ?,email = ? where id = ?";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //定义sql语句
        String sql = "select count(*) from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //遍历map
        Set<String> KeySet = condition.keySet();
        //定义参数集合
        List<Object> params = new ArrayList<Object>();
        for (String key : KeySet) {
            //排除分页条件参数
            if ("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if (value!=null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add(value);//?条件值
            }
        }
        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //遍历map
        Set<String> KeySet = condition.keySet();
        //定义参数集合
        List<Object> params = new ArrayList<Object>();
        for (String key : KeySet) {
            //排除分页条件参数
            if ("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if (value!=null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add(value);//?条件值
            }
        }
        //添加分页的查询
        sb.append(" limit ?,? ");
        //添加分页查询分数值
        params.add(start);
        params.add(rows);
        return template.query(sb.toString(),new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }


}
