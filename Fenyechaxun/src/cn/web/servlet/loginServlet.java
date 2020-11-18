package cn.web.servlet;

import cn.domain.User;
import cn.service.UserService;
import cn.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取数据
        String verifycode = request.getParameter("verifycode");
        //校验验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        //为了保证验证码是一次性的
        session.removeAttribute("CHECKCODE_SERVER");
        if (!checkcode_server.equalsIgnoreCase(verifycode)){
            //提示信息
            request.setAttribute("login_msg","验证码错误！");
            //跳转登录界面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        //封装user对象
        User user = new User();
        Map<String, String[]> map = request.getParameterMap();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //利用service查询
        UserService service = new UserServiceImpl();
        User loginUser = service.login(user);
        //判断是否登录成功
        if (loginUser!=null){
            //登录成功
            //将用户存入session
            session.setAttribute("user",loginUser);
            //跳转页面采用重定向
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
        else {
            //登录失败
            //提示信息
            request.setAttribute("login_msg","用户名或密码错误!");

            //跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            System.out.println(user);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
