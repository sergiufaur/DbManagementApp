package com.utcn.Sergiu.services;

import com.utcn.Sergiu.model.Product;
import com.utcn.Sergiu.repositories.ClientRepository;
import com.utcn.Sergiu.repositories.ProductRepository;

import java.util.List;

public class ProductServiceImpl implements ProductService{
    private final ProductRepository repsitory;

    public ProductServiceImpl(ProductRepository repsitory) {
        this.repsitory = repsitory;
    }
    @Override
    public Long add(String name, int stock, int price) {
        return repsitory.create(name,stock,price);
    }

    @Override
    public void edit(Product product) {
        Product toEditProduct =repsitory.read(product.getId());
        if(!product.getName().isBlank())
        {
            toEditProduct.setName(product.getName());
        }
        if(product.getStock()>=0)
        {
            toEditProduct.setStock(product.getStock());
        }
        if(product.getPrice()>=0){
            toEditProduct.setPrice(product.getPrice());
        }
        repsitory.update(toEditProduct);
    }

    @Override
    public void delete(Long id) {
         repsitory.delete(id);
    }

    @Override
    public List<Product> viewAll() {
        return repsitory.list();
    }
}
