package web;

import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {


    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        if ("abcd".equalsIgnoreCase(code)){
            if (userService.existsUsername(username)){
                //不可用
                System.out.println("用户名不可用");
                req.setAttribute("message","用户名已存在");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);

            }else{
                //可用
                userService.registUser(new User(null,username,password,email));

                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }


        }else {
            req.setAttribute("message","验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("code",code);
            req.setAttribute("email",email);
            System.out.println("验证码["+code+"]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }

    }
}
