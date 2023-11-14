package br.com.fiap.protecaopesada.resource;

import java.sql.SQLException;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import br.com.fiap.protecaopesada.exception.BadInfoException;
import br.com.fiap.protecaopesada.exception.IdNotFoundException;
import br.com.fiap.protecaopesada.model.Veiculo;
import br.com.fiap.protecaopesada.service.VeiculoService;

@Path("/veiculo")
public class VeiculoResource {

    private VeiculoService service;

    public VeiculoResource() throws ClassNotFoundException, SQLException {
        service = new VeiculoService();
    }

    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    public Veiculo pesquisar(@QueryParam("nome") String pesquisa) throws SQLException, ClassNotFoundException, IdNotFoundException {
        return service.pesquisar(pesquisa);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Veiculo> lista() throws ClassNotFoundException, SQLException {
        return service.listar();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response busca(@PathParam("modelo") String modelo) throws ClassNotFoundException, SQLException, IdNotFoundException {
        return Response.ok(service.pesquisarPorModelo(modelo)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Veiculo veiculo, @Context UriInfo uri) throws ClassNotFoundException, SQLException, IdNotFoundException {
        try {
            service.cadastrar(veiculo);
            UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
            uriBuilder.path(String.valueOf(veiculo.getId()));
            return Response.created(uriBuilder.build()).build();
        } catch (BadInfoException e) {
            e.printStackTrace();
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(Veiculo veiculo, @PathParam("id") int id) throws ClassNotFoundException, SQLException {
        try {
            veiculo.setId(id);
            service.atualizar(veiculo);
            return Response.ok().build();
        } catch (IdNotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        } catch (BadInfoException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        try {
            service.remover(id);
            return Response.noContent().build();
        } catch (IdNotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }
}
