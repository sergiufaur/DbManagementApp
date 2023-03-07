package com.utcn.Sergiu.repositories;

import com.utcn.Sergiu.model.Client;

import java.util.List;

public interface ClientRepository {
    Long create(String name ,String adress);

    Client read(Long id);

    void update(Client client);

    void delete(Long id);

    List<Client> list();
}
