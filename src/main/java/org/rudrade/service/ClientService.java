package org.rudrade.service;

import java.util.List;

import org.rudrade.entity.Client;
import org.rudrade.repository.ClientRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClientService {
    
    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<Client> findAll() {
        return repository.findAll().list();
    }

    @Transactional
    public Client persist(Client client) {
        if (client.id <= 0) {
            client.persist();
        } else {
            Client entity = repository.findById(client.id);
            entity.copy(client);
            entity.persist();
        }
        
        return client;
    }

    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

}
