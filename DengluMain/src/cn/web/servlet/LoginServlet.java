package cn.web.servlet;

import cn.dao.UserDao;
import cn.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //设置编码
//        req.setCharacterEncoding("utf-8");
//        //获取请求参数
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        //封装对象
//        User loginuser = new User();
//        loginuser.setUsername(username);
//        loginuser.setPassword(password);
        //获取室所有请求参数
        Map<String, String[]> map = req.getParameterMap();
        User loginuser = new User();
        try {
            BeanUtils.populate(loginuser,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用login中的dao方法
        UserDao dao = new UserDao();
        User user = dao.login(loginuser);
        //判断user
        if (user==null){
            //登录失败
            req.getRequestDispatcher("/failServlet").forward(req,resp);
        }
        else {
            //登录成功
            //存储数据
            req.setAttribute("user",user);
            //转发
            req.getRequestDispatcher("/successServlet").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
