package br.pro.delfino.drogariav3.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.pro.delfino.drogariav3.dao.FabricanteDAO;
import br.pro.delfino.drogariav3.dao.ProdutoDAO;
import br.pro.delfino.drogariav3.domain.Fabricante;
import br.pro.delfino.drogariav3.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean2 implements Serializable {
	private List<Produto> produtos;
	private ProdutoDAO dao;
	private Produto produto;
	private List<Fabricante> fabricantes;
	private FabricanteDAO fabricanteDAO;
	private Long codigoProduto;

	@PostConstruct
	public void iniciar() {
		this.dao = new ProdutoDAO();
		this.fabricanteDAO = new FabricanteDAO();
	}

	public void listar() {
		try {
			this.produtos = this.dao.listar("descricao");
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os produtos");
			e.printStackTrace();
		}
	}

	public void carregarEdicao() {
		try {
			this.produto = this.dao.buscar(this.codigoProduto);
			this.fabricantes = this.fabricanteDAO.listar("descricao");
		} catch (Exception e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar os dados para edição");
			e.printStackTrace();
		}
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public Long getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(Long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
}
