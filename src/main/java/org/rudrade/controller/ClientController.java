package org.rudrade.controller;

import java.util.List;

import org.rudrade.entity.Client;
import org.rudrade.service.ClientService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/client")
@Produces(MediaType.APPLICATION_JSON)
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GET
    public List<Client> getClients() {
        return service.findAll();
    }

}
