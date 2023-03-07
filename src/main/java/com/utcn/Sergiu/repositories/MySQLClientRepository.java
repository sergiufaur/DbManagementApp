package com.utcn.Sergiu.repositories;

import com.utcn.Sergiu.database.ConnectionFactory;
import com.utcn.Sergiu.model.Client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MySQLClientRepository extends GenericRepository<Client> implements ClientRepository {
    private ConnectionFactory connectionFactory;

    public MySQLClientRepository(ConnectionFactory connectionFactory) {
        super(connectionFactory);
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Long create(String name ,String adress) {
        Client client =new Client();
        client.setAdress(adress);
        client.setName(name);

        return super.create(client);
    }

    @Override
    public Client read(Long id) {

        return super.findById(id);
    }

    @Override
    public void update(Client client) {
          super.update(client);
    }

    @Override
    public void delete(Long id) {
         super.delete(id);
    }

    @Override
    public List<Client> list() {
        return super.list();
    }
}
