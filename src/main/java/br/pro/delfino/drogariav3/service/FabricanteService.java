package br.pro.delfino.drogariav3.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.pro.delfino.drogariav3.dao.FabricanteDAO;
import br.pro.delfino.drogariav3.domain.Fabricante;

@Path("fabricante")
public class FabricanteService {
	@GET
	public String listar() {
		FabricanteDAO dao = new FabricanteDAO();
		List<Fabricante> fabricantes = dao.listar("descricao");
		Gson gson = new Gson();
		return gson.toJson(fabricantes);
	}

	@GET
	@Path("{codigo}")
	public String buscar(@PathParam("codigo") Long codigo) {
		FabricanteDAO dao = new FabricanteDAO();
		Fabricante fabricante = dao.buscar(codigo);
		Gson gson = new Gson();
		return gson.toJson(fabricante);
	}

	@POST
	public String salvar(String json) {
		Gson gson = new Gson();
		Fabricante fabricante = gson.fromJson(json, Fabricante.class);
		FabricanteDAO dao = new FabricanteDAO();
		dao.salvar(fabricante);
		return gson.toJson(fabricante);
	}

	@PUT
	public String editar(String json) {
		Gson gson = new Gson();
		Fabricante fabricante = gson.fromJson(json, Fabricante.class);
		FabricanteDAO dao = new FabricanteDAO();
		dao.editar(fabricante);
		return gson.toJson(fabricante);
	}

	@DELETE
	@Path("{codigo}")
	public String excluir(@PathParam("codigo") Long codigo) {
		FabricanteDAO dao = new FabricanteDAO();
		Fabricante fabricante = dao.buscar(codigo);
		dao.excluir(fabricante);
		Gson gson = new Gson();
		return gson.toJson(fabricante);
	}
}
