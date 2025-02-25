package org.rudrade.controller;

import java.util.List;

import org.rudrade.entity.Client;
import org.rudrade.service.ClientService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/client")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GET
    public List<Client> getClients() {
        return service.findAll();
    }

    @POST
    public Client createClient(Client client) {
        return service.persist(client);
    }

    @DELETE
    @Path("/{id}")
    public void deleteClient(@PathParam("id") long id) {
        service.delete(id);
    }

}
