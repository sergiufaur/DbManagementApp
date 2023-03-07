package com.utcn.Sergiu.repositories;

import com.utcn.Sergiu.model.Client;
import com.utcn.Sergiu.model.Order;

import java.util.List;

public interface OrderRepository {
    Long create(Long idClient,Long idProduct,int quantity,int price);

    Order read(Long id);

    void update(Order order);

    void delete(Long id);

    List<Order> list();
}
