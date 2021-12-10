package test;

import dao.OrderItemDao;
import dao.impl.OrderItemDaoImpl;
import org.junit.Test;
import pojo.OrderItem;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();

        orderItemDao.saveOrderItem(new OrderItem(null,"java从入门到精通", 1,new BigDecimal(100),new BigDecimal(100),"123443232"));
        orderItemDao.saveOrderItem(new OrderItem(null,"javaScript从入门到精通", 2,new BigDecimal(100),new BigDecimal(200),"123443232"));
        orderItemDao.saveOrderItem(new OrderItem(null,"Netty入门", 1,new BigDecimal(100),new BigDecimal(100),"123443232"));
    }
}