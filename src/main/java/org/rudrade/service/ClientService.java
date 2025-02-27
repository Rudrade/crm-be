package org.rudrade.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
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

    public File exportClients() throws IOException {
        
        File file = new File(System.getProperty("java.io.tmpdir") + File.separator + "clients.csv");
        try {
            try (
                FileWriter writer = new FileWriter(file);
                CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            ) {
                List<Client> lst = findAll();

                printer.printRecord("id", "first_name", "last_name", "email");

                for (Client client : lst) {
                    printer.printRecord(
                        client.id,
                        client.firstName,
                        client.lastName,
                        client.email
                    );
                }
            }

        } catch (Exception e) {
            if (file != null) {
                try {
                    Files.deleteIfExists(file.toPath());
                } catch (IOException e2) {
                    System.err.println("Erro ao apagar fx"+ e2);
                }
            }

            throw e;
        }

        return file;
    }

}
