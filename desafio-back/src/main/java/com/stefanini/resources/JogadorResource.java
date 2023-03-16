package com.stefanini.resources;

import com.stefanini.entity.Jogador;
import com.stefanini.mappers.JogadorMapperImpl;
import com.stefanini.mappers.JogadorMapperInterface;
import com.stefanini.service.JogadorService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
@Path("/jogador")
public class JogadorResource {

    @Inject
    JogadorService jogadorService;

    @Inject
    JogadorMapperInterface jogadorMapper;
    @GET
    @Path("/{id}")
    public Response pegarPorId(@PathParam("id") Long id){
        return Response.status(Response.Status.OK).entity(jogadorMapper.paraJogadorDTO(jogadorService.pegarPorId(id))).build();
    }

    @GET
    @Path("/todos")
    public Response listarTodos(){
        return Response.status(Response.Status.OK).entity(jogadorMapper.paraListaDeJogadoresDTO(jogadorService.listarTodos())).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response salvar(@Valid Jogador jogador) {
        jogadorService.salvar(jogador);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response alterar(@PathParam("id") Long id,@Valid Jogador jogador) {
        jogadorService.alterar(jogador,id);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        jogadorService.deletar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/escolher/{id}")
    public Response escolher(@PathParam("id") Long idJogador,Long idStefamon) {
        jogadorService.escolherStefamon(idJogador,idStefamon);
        return Response.status(Response.Status.OK).build();
    }
    
    @GET
    @Path("/batalhar")
    public Response batalhar(@Valid Jogador jogador1, @Valid Jogador jogador2) {
        return Response.status(Response.Status.OK).entity(jogadorService.batalhar(jogador1,jogador2)).build();
    }

    @GET
    @Path("/batalhar/bot")
    public Response batalharBot(@Valid Jogador jogador1) {
        return Response.status(Response.Status.OK).entity(jogadorService.batalharBot(jogador1)).build();
    }




}
