package com.utcn.Sergiu;

import com.utcn.Sergiu.controllers.Controller;
import com.utcn.Sergiu.database.ConnectionFactory;
import com.utcn.Sergiu.model.Client;
import com.utcn.Sergiu.model.Order;
import com.utcn.Sergiu.model.Product;
import com.utcn.Sergiu.repositories.*;

import com.utcn.Sergiu.services.*;
import com.utcn.Sergiu.views.View;
import com.utcn.Sergiu.views.ViewClient;
import com.utcn.Sergiu.views.ViewOrder;
import com.utcn.Sergiu.views.ViewProduct;

import java.util.List;


public class Application {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        ClientRepository clientRepository = new MySQLClientRepository(connectionFactory);
        ProductRepository productRepository =new MySQLProductRepository(connectionFactory);
        OrderRepository orderRepository = new MySQLOrderRepository(connectionFactory);
        ClientService clientService = new ClientServiceImpl(clientRepository);
        ProductService productService=new ProductServiceImpl(productRepository);
        OrderService orderService=new OrderServiceImpl(orderRepository);
        View view = new View();
        ViewClient viewClient=new ViewClient();
        ViewProduct viewProduct=new ViewProduct();
        ViewOrder viewOrder=new ViewOrder();
        Controller controller = new Controller(view,viewClient,viewProduct,viewOrder, clientService,productService,orderService);




    }

}
