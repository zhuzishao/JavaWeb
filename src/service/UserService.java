package service;

import pojo.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 用户登录
     * @param user
     * @return 登录失败，则返回null
     */
    public User login(User user);

    /**
     * 验证用户是否存在
     * @param username
     * @return返回true代表用户名已存在，返回false表示用户名可用
     */
    public boolean existsUsername(String username);
}
