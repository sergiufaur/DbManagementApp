package com.utcn.Sergiu.services;

import com.utcn.Sergiu.model.Order;
import com.utcn.Sergiu.repositories.OrderRepository;
import com.utcn.Sergiu.repositories.ProductRepository;

import java.util.List;

public class OrderServiceImpl implements OrderService{
    private final OrderRepository repsitory;

    public OrderServiceImpl(OrderRepository repsitory) {
        this.repsitory = repsitory;
    }
    @Override
    public Long add(Long idClient, Long idProduct, int quantity, int price) {
        return repsitory.create(idClient,idProduct,quantity,price);
    }

    @Override
    public void edit(Order order) {

        Order toEditOrder =repsitory.read(order.getId());
        if(order.getIdClient()>0 && order.getIdClient() !=null){
            toEditOrder.setIdClient(order.getIdClient());
        }
        if(order.getIdProduct()>0 && order.getIdProduct() !=null){
            toEditOrder.setIdProduct(order.getIdProduct());
        }
        if(order.getQuantity()>=0)
        {
            toEditOrder.setQuantity(order.getQuantity());
        }
        if(order.getPrice()>=0){
            toEditOrder.setPrice(order.getPrice());
        }
        repsitory.update(toEditOrder);
    }

    @Override
    public void delete(Long id) {
        repsitory.delete(id);
    }

    @Override
    public List<Order> viewAll() {
        return repsitory.list();
    }
}
