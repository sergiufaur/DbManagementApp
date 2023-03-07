package com.utcn.Sergiu.services;

import com.utcn.Sergiu.model.Client;

import java.util.List;

public interface ClientService {
    Long add(String name ,String adress);

    void edit(Client client);

    void delete(Long id);

    List<Client> viewAll();
}
