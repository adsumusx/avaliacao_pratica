package br.desafio.dao;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import br.fazenda.pgfn.sicoe.entity.CodigoDotacao;
import br.desafio.entity.Operador;
import br.desafio.util.JPAUtil;
import br.desafio.util.Result;
import br.desafio.util.ResultError;

public class OperadorDao {
	private static EntityManager entityManager = null;

	public OperadorDao() {
		entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	public String Consultar(String sql, HttpServletRequest request)
			throws JsonParseException, JsonMappingException, IOException {
		JPAUtil.startTransaction(entityManager);
		TypedQuery<Operador> query = entityManager.createQuery(sql, Operador.class);

		try {

			if (query.getResultList() != null) {
				ObjectMapper mapper = new ObjectMapper();
				Long count = Count("SELECT COUNT(*) FROM Operador o", request);
				Result resultado = new Result();

				if (count > 0) {
					resultado.type = "success";
					resultado.status = 200;
					resultado.results = query.getResultList();
					resultado.result_count = count;
				} else {
					ResultError error = new ResultError();
					error.error_message = "Nenhum registro encontrado";
					error.type = "Not Found";
					resultado.type = "error";
					resultado.status = 404;
					resultado.results = error;
					resultado.result_count = count;
				}
				String jsonInString = mapper.writerWithView(Result.class).writeValueAsString(resultado);
				return jsonInString;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.finishTransacton(entityManager);
		}
		return null;

	}

	public String ConsultarPorId(Integer idOperador, HttpServletRequest request)
			throws JsonParseException, JsonMappingException, IOException {
		JPAUtil.startTransaction(entityManager);
		TypedQuery<Operador> query = entityManager
				.createQuery("SELECT o from Operador o WHERE o.id ='" + idOperador + "'", Operador.class);
		try {

			if (query.getResultList() != null) {
				ObjectMapper mapper = new ObjectMapper();
				Long count = Count("SELECT COUNT(*) FROM Operador o", request);
				Result resultado = new Result();

				if (count > 0) {
					resultado.type = "success";
					resultado.status = 200;
					resultado.results = query.getResultList();
					resultado.result_count = count;
				} else {
					ResultError error = new ResultError();
					error.error_message = "Nenhum registro encontrado";
					error.type = "Not Found";
					resultado.type = "error";
					resultado.status = 404;
					resultado.results = error;
				}
				String jsonInString = mapper.writerWithView(Result.class).writeValueAsString(resultado);
				return jsonInString;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.finishTransacton(entityManager);
		}
		return null;

	}

	public Operador Alterar(Operador operador, HttpServletRequest request)
			throws JsonParseException, JsonMappingException, IOException {
		try {
			entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
			JPAUtil.startTransaction(entityManager);
			
			entityManager.persist(operador);

			return operador;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.finishTransacton(entityManager);
		}
		return null;
	}

	public Operador Salvar(Operador operador, HttpServletRequest request)
			throws JsonParseException, JsonMappingException, IOException {
		try {
			JPAUtil.startTransaction(entityManager);
			entityManager.persist(operador);
			return operador;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JPAUtil.finishTransacton(entityManager);
		}
		return null;
	}

	public Long Count(String sql, HttpServletRequest request)
			throws JsonParseException, JsonMappingException, IOException {
		try {

			Query query = entityManager.createQuery(sql);
			Long count = (Long) query.getSingleResult();
			return count;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public Operador Excluir(Long idOperador, HttpServletRequest request)
			throws JsonParseException, JsonMappingException, IOException {
		try {
			JPAUtil.startTransaction(entityManager);
			Operador operador = entityManager.find(Operador.class, idOperador);
			Result resultado = new Result();

			entityManager.remove(operador);

			if (operador != null) {
				resultado.type = "success";
				resultado.status = 200;
				resultado.results = operador;
			} else {
				ResultError error = new ResultError();
				error.error_message = "Erro ao excluir registro";
				error.type = "Erro interno do servidor";
				resultado.type = "error";
				resultado.status = 500;
				resultado.results = error;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.finishTransacton(entityManager);
		}
		return null;

	}
}
