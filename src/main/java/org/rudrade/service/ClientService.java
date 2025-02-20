package org.rudrade.service;

import java.util.List;

import org.rudrade.entity.Client;
import org.rudrade.repository.ClientRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientService {
    
    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<Client> findAll() {
        return repository.findAll().list();
    }

}
