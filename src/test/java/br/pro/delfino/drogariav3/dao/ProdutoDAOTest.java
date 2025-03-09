package br.pro.delfino.drogariav3.dao;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogariav3.domain.Fabricante;
import br.pro.delfino.drogariav3.domain.Produto;

public class ProdutoDAOTest {
	@Test
	@Ignore
	public void salvar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(3L);
		Produto produto = new Produto();
		produto.setDescricao("Cataflan 50mg com 20 Comprimidos");
		produto.setFabricante(fabricante);
		produto.setPreco(new BigDecimal("13.70"));
		produto.setQuantidade(Short.valueOf("7"));
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
		System.out.println("Produto salvo com sucesso");
	}
}
