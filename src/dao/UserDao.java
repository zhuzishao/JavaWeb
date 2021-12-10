package dao;

import pojo.User;


public interface UserDao {



    /**
     * 根据用户名和密码查询用户信息
     * @param username
     * @return 如果返回null，说明用户名或者密码错误
     */
    public User queryUserByUsernameAndPassword (String username,String password);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return如果返回null，说明用户名或者密码错误
     */
    public User queryUserByUsername(String username);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public  int saveUser(User user);

}
