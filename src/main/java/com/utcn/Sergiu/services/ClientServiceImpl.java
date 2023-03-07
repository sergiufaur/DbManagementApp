package com.utcn.Sergiu.services;

import com.utcn.Sergiu.model.Client;
import com.utcn.Sergiu.model.Product;
import com.utcn.Sergiu.repositories.ClientRepository;

import java.util.List;

public class ClientServiceImpl implements ClientService{
    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repsitory) {
        this.repository = repsitory;
    }

    @Override
    public Long add(String adress ,String name) {
         return repository.create(adress,name);
    }

    @Override
    public void edit(Client client) {
        Client toEditClient =repository.read(client.getId());
        if(!client.getName().isBlank())
        {
            toEditClient.setName(client.getName());
        }
        if(!client.getAdress().isBlank()){
            toEditClient.setAdress(client.getAdress());
        }
        repository.update(toEditClient);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public List<Client> viewAll() {
       return  repository.list();
    }
}
