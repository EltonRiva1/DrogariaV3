package br.pro.delfino.drogariav3.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.google.gson.Gson;

import br.pro.delfino.drogariav3.dao.EstadoDAO;
import br.pro.delfino.drogariav3.domain.Estado;

@Path("estado")
public class EstadoService {
	@GET
	public String listar() {
		EstadoDAO dao = new EstadoDAO();
		List<Estado> estados = dao.listar("nome");
		Gson gson = new Gson();
		return gson.toJson(estados);
	}
}
