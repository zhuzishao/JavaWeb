package web;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.google.gson.Gson;
import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;
import test.UserServiceTest;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet{

    private UserService userService = new UserServiceImpl();


    /**
     * ajax请求实现用户登录自动验证
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数username
        String username = req.getParameter("username");
        // 调用userService.existsUsername();
        boolean existsUsername = userService.existsUsername(username);
        // 把返回的结果封装成为map对象
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }

    /**
     * 登录功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User login = userService.login(new User(null, username, password, null));
        if (login == null){
            //返回注册页面
            req.setAttribute("message","用户名或密码错误");
            req.setAttribute("username",username);
            System.out.println("登录失败");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);

        }else {

            // 将用户登录信息保存到session域中
            req.getSession().setAttribute("user",login);
            //转到登录成功页面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    /**
     * 注销功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */



    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 销毁session中用户登录的信息
        req.getSession().invalidate();
        // 重定向到首页或者登录页面
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * 注册功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
        protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
            // 获取谷歌验证码
            String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
            req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            String code = req.getParameter("code");

            User user =  WebUtils.copyParamToBean(req.getParameterMap(),new User());


            if (token != null && token.equalsIgnoreCase(code)){
                if (userService.existsUsername(username)){
                    //不可用
                    System.out.println("用户名不可用");
                    req.setAttribute("message","用户名已存在");
                    req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);

                }else{
                    //可用
                    userService.registUser(new User(null,username,password,email));
                    // 将用户登录信息保存到session域中
                    req.getSession().setAttribute("user",user);

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





   /* protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action =req.getParameter("action");
        if ("login".equals(action)){
            login(req,resp);
        }else if ("regist".equals(action)){
            regist(req,resp);
        }
    }*/
}
