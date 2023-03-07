package com.utcn.Sergiu.repositories;

import com.utcn.Sergiu.database.ConnectionFactory;
import com.utcn.Sergiu.model.Client;
import com.utcn.Sergiu.model.Product;

import java.util.List;

public class MySQLProductRepository extends GenericRepository<Product> implements ProductRepository {
    private ConnectionFactory connectionFactory;
    public MySQLProductRepository(ConnectionFactory connectionFactory) {
        super(connectionFactory);
        this.connectionFactory=connectionFactory;
    }

    @Override
    public Long create(String name, int stock, int price) {
        Product product=new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        return super.create(product);

    }

    @Override
    public Product read(Long id) {
        return (Product) super.findById(id);
    }

    @Override
    public void update(Product product) {
        super.update(product);
    }
    public List<Product> list(){
        return super.list();
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }
}
