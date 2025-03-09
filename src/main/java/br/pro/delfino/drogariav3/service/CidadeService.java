package br.pro.delfino.drogariav3.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.pro.delfino.drogariav3.dao.CidadeDAO;
import br.pro.delfino.drogariav3.domain.Cidade;

@Path("cidade")
public class CidadeService {
	@GET
	@Path("{estadoCodigo}")
	public String buscarPorEstado(@PathParam("estadoCodigo") Long estadoCodigo) {
		CidadeDAO dao = new CidadeDAO();
		List<Cidade> cidades = dao.buscarPorEstado(estadoCodigo);
		Gson gson = new Gson();
		return gson.toJson(cidades);
	}
}
