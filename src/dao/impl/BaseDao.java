package dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public  abstract class BaseDao {
    private QueryRunner queryRunner = new QueryRunner();


    /**
     * 添加用户注册信息
     * @param sql
     * @param args
     * @return
     */
    public  int update(String sql,Object...args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return -1;
    }


    /**
     *查询返回一个javaBean的sql语句
     * @param type  返回对象类型
     * @param sql   执行的sql语句
     * @param args  sql语句对应的参数值
     * @param <T>   返回的类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type,String sql,Object...args){
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public <T>List<T> queryForList(Class<T> type,String sql,Object...args){
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }

    }

    /**
     * 执行返回一行一列的sql语句
     * @param sql   执行的sql语句
     * @param args  对应的参数值
     * @return
     */

    public Object queryForSingleValue(String sql,Object...args){
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
    }


}
