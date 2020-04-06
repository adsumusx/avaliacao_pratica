package br.desafio.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.desafio.dao.OperadorDao;
//import br.fazenda.pgfn.sicoe.entity.CodigoDotacao;
import br.desafio.entity.Operador;
import br.desafio.enums.TipoPerfil.MyRole;
import br.desafio.util.Result;
import br.desafio.util.ResultError;

// Aqui se passa o Path para acessar as operações desse arquivo.
// Para se utilizar os metodos dessa classe pelo rest, deve-se usar:
// "localhost:8080/ProjetoBase-0.0.1-SNAPSHOT/api/examples/...".
@Path("/operador")
public class OperadorRest {
	
	private final OperadorDao repository = new OperadorDao();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listaOperador")
	public Response getOperadores(@Context HttpServletRequest request)
			throws JsonParseException, JsonMappingException, IOException {
		String resultadoOperador = repository.Consultar("SELECT o FROM Operador o", request);
		return Response.status(200).entity(resultadoOperador).build();

	}

	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/setOperador")
	public Response setOperador(Operador _operador, @Context HttpServletRequest request)
			throws JsonParseException, JsonMappingException, IOException {
		try {
			Operador operador = repository.Salvar(_operador, request);
			Result resultado = new Result();
			if (operador != null) {
				resultado.type = "success";
				resultado.status = 200;
				resultado.results = operador;

			} else {
				ResultError error = new ResultError();
				error.error_message = "Erro ao persistir registro.";
				error.type = "Not found";
				resultado.type = "error";
				resultado.status = 404;
				resultado.results = error;
			}

			return Response.status(201).entity(resultado).build();

		} catch (Exception e) {
			ResultError error = new ResultError();
			error.error_message = e.getMessage();
			error.type = e.getClass().getSimpleName();
			return Response.status(500).entity(error).build();
		}
	}
	
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/getOperador/{idOperador}")
	public Response getOperador(@PathParam("idOperador") Integer idOperador, @Context HttpServletRequest request) 
			throws JsonParseException, JsonMappingException, IOException {

		String resultadoFuncao = repository.ConsultarPorId(idOperador, request);
		return Response.status(200).entity(resultadoFuncao).build();

	}
	
	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/putOperador/{idOperador}")
	public Response Alterar(@PathParam("idOperador") Long idOperador, Operador _operador, @Context HttpServletRequest request) 
			throws JsonParseException, JsonMappingException, IOException  {

		try {
			Operador operador = new Operador();
			operador.setNomeOperador(_operador.getNomeOperador());
			operador.setLoginOperador(_operador.getLoginOperador());
			operador.setSenhaOperador(_operador.getSenhaOperador());
						
			repository.Excluir(idOperador,request);
            repository.Alterar(operador, request);

            Result resultado = new Result();
			if (operador != null) {
				resultado.type = "success";
				resultado.status = 200;
				resultado.results = operador;
				
			} else {
				ResultError error = new ResultError();
				error.error_message = "Erro ao atualizar registro";
				error.type = "Erro interno do servidor";
				resultado.type = "error";
				resultado.status = 500;
				resultado.results = error;
			}
			
			return Response.status(201).entity(resultado).build();

        } catch (Exception e) {
            ResultError error = new ResultError();
            error.error_message = e.getMessage();
            error.type = e.getClass().getSimpleName();
            return Response.status(500).entity(error).build();

        }

    }
	
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("/deleteOperador/{idOperador}")
	public Response Excluir(@PathParam("idOperador") Long idOperador, @Context HttpServletRequest request) 
			throws JsonParseException, JsonMappingException, IOException  {

		try {
			repository.Excluir(idOperador, request);
			String resposta = "Registro apagado com sucesso!";
			return Response.status(201).entity(resposta).build();
		} catch (Exception e) {
			ResultError error = new ResultError();
			error.error_message = "Erro ao deletar registro.";
			error.type = "Not Found";
			Result resultado = new Result();
			resultado.type = "error";
			resultado.status = 404;
			resultado.results = error;
			return Response.status(404).entity(resultado).build();
		}

	}

}
