package test;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import org.junit.Test;
import pojo.Order;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.saveOrder(new Order("123443232",new Date(),new BigDecimal(100),0,5));
    }
}