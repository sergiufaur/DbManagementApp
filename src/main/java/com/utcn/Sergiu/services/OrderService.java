package com.utcn.Sergiu.services;

import com.utcn.Sergiu.model.Client;
import com.utcn.Sergiu.model.Order;

import java.util.List;

public interface OrderService {
    Long add(Long idClient,Long idProduct,int quantity,int price);

    void edit(Order order);

    void delete(Long id);

    List<Order> viewAll();
}
