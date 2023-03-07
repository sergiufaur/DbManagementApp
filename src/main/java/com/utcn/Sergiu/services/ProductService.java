package com.utcn.Sergiu.services;

import com.utcn.Sergiu.model.Client;
import com.utcn.Sergiu.model.Product;

import java.util.List;

public interface ProductService {
    Long add( String name, int stock,int price);

    void edit(Product product);

    void delete(Long id);

    List<Product> viewAll();
}
