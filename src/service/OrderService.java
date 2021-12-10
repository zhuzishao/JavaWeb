package service;

import pojo.Cart;

public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
}
