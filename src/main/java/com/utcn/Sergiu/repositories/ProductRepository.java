package com.utcn.Sergiu.repositories;

import com.utcn.Sergiu.model.Client;
import com.utcn.Sergiu.model.Product;

import java.util.List;

public interface ProductRepository {
    Long create(String name, int stock,int price);

    Product read(Long id);

    void update(Product product);

    void delete(Long id);

    List<Product> list();
}
