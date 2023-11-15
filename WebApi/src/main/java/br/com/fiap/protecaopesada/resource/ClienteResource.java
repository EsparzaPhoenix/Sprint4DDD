package br.com.fiap.protecaopesada.resource;

import java.sql.SQLException;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import br.com.fiap.protecaopesada.exception.BadInfoException;
import br.com.fiap.protecaopesada.exception.IdNotFoundException;
import br.com.fiap.protecaopesada.model.Cliente;
import br.com.fiap.protecaopesada.service.ClienteService;

@Path("/clientes")
public class ClienteResource {

    private ClienteService service;

    public ClienteResource() throws ClassNotFoundException, SQLException, BadInfoException, IdNotFoundException {
        service = new ClienteService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> listarClientes() throws SQLException, IdNotFoundException {
        return service.listarClientes();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarCliente(Cliente cliente, @Context UriInfo uri) throws SQLException, IdNotFoundException {
        try {
            service.cadastrarCliente(cliente);
            UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
            uriBuilder.path(String.valueOf(cliente.getCodigo()));
            return Response.created(uriBuilder.build()).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pesquisarCliente(@PathParam("codigo") int codigo) throws SQLException, IdNotFoundException {
        try {
            Cliente cliente = service.pesquisarCliente(codigo);
            return Response.ok(cliente).build();
        } catch (IdNotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{codigo}")
    public Response removerCliente(@PathParam("codigo") int codigo) throws SQLException, IdNotFoundException {
        try {
            service.removerCliente(codigo);
            return Response.noContent().build();
        } catch (IdNotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }
}
