package com.utcn.Sergiu.repositories;

import com.utcn.Sergiu.model.Client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public class InMemoryClientRepository implements ClientRepository {
    private List<Client> clients = new ArrayList<>();

    @Override
    public Long create(String name, String adress) {
        Client client =new Client();
        long maxId = clients.stream().mapToLong(c -> c.getId()).max().orElse(0);
        client.setId(maxId+1);
        client.setName(name);
        client.setAdress(adress);
        clients.add(client);

        return maxId+1;
    }

    @Override
    public Client read(Long id) {

       if(id==null)
           return null;
        for (Client client:clients
             ) {
            if(id.equals(client.getId())){
                return client;
            }
        }
        return null;
    }

    @Override
    public void update(Client client) {
        for (Client c:clients
        ) {
            if(c.getId().equals(client.getId())){
                c.setName(client.getName());
                c.setAdress(client.getAdress());
            }
        }
    }

    @Override
    public void delete(Long id) {
        clients.remove(new Client());
    }

    @Override
    public List<Client> list() {
        return Collections.unmodifiableList(clients);
    }
}
