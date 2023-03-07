package com.utcn.Sergiu.repositories;

import com.utcn.Sergiu.database.ConnectionFactory;
import com.utcn.Sergiu.model.Client;
import com.utcn.Sergiu.model.Order;

import java.util.List;

public class MySQLOrderRepository extends GenericRepository<Order> implements OrderRepository{
    private ConnectionFactory connectionFactory;
    public MySQLOrderRepository(ConnectionFactory connectionFactory) {
        super(connectionFactory);
        this.connectionFactory=connectionFactory;
    }
    @Override
    public Long create(Long idClient, Long idProduct, int quantity, int price) {
        Order order=new Order();
        order.setIdClient(idClient);
        order.setIdProduct(idProduct);
        order.setPrice(price);
        order.setQuantity(quantity);
        return super.create(order);
    }

    @Override
    public Order read(Long id) {
        return super.findById(id);
    }

    @Override
    public void update(Order order) {
         super.update(order);
    }

    @Override
    public List<Order> list() {
        return super.list();
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }
}
