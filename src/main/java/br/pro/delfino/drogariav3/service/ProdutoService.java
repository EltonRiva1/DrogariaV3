package br.pro.delfino.drogariav3.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.google.gson.Gson;

import br.pro.delfino.drogariav3.dao.ProdutoDAO;
import br.pro.delfino.drogariav3.domain.Produto;

@Path("produto")
public class ProdutoService {
	@GET
	public String listar() {
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> produtos = dao.listar("descricao");
		Gson gson = new Gson();
		String json = gson.toJson(produtos);
		return json;
	}

	@POST
	public String salvar(String json) {
		Gson gson = new Gson();
		Produto produto = gson.fromJson(json, Produto.class);
		ProdutoDAO dao = new ProdutoDAO();
		produto = dao.merge(produto);
		String jsonSaida = gson.toJson(produto);
		return jsonSaida;
	}
}
