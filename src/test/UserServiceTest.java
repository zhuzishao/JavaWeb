package test;

import org.junit.Test;
import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null,"moshui","moshui","moshui@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"nana","nana",null)));
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("nana")){
            System.out.println("用户名已存在");
        }else {
            System.out.println("用户名可用");
        }

    }
}