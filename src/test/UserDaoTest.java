package test;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import org.junit.Test;
import pojo.User;

import static org.junit.Assert.*;

public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsernameAndPassword() {

        if (userDao.queryUserByUsernameAndPassword("admin","admin") == null){

            System.out.println("用户名或者密码错误");
        }else {
            System.out.println("登录成功");
        }
    }

    @Test
    public void queryUserByUsername() {

        System.out.println(userDao.queryUserByUsername("admin"));

        if (userDao.queryUserByUsername("admin") == null){
            System.out.println("用户名可用");
        }else {
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"nana","nana","nana@qq.com")));
    }
}