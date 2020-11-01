package cn.test;

import cn.dao.UserDao;
import cn.domain.User;
import org.junit.Test;

public class UserTest {
    @Test
    public void testLogin(){
        User loginUser = new User();
        loginUser.setUsername("youzi");
        loginUser.setPassword("123");
        UserDao dao = new UserDao();
        User user = dao.login(loginUser);
        System.out.println(user);

    }
}
