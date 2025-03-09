package br.pro.delfino.drogariav3.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.delfino.drogariav3.dao.ClienteDAO;
import br.pro.delfino.drogariav3.dao.FuncionarioDAO;
import br.pro.delfino.drogariav3.dao.ProdutoDAO;
import br.pro.delfino.drogariav3.dao.VendaDAO;
import br.pro.delfino.drogariav3.domain.Cliente;
import br.pro.delfino.drogariav3.domain.Funcionario;
import br.pro.delfino.drogariav3.domain.ItemVenda;
import br.pro.delfino.drogariav3.domain.Produto;
import br.pro.delfino.drogariav3.domain.Venda;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable {
	private Venda venda;
	private List<Produto> produtos;
	private List<ItemVenda> itensVenda;
	private List<Cliente> clientes;
	private List<Funcionario> funcionarios;
	private List<Venda> vendas;

	public void novo() {
		try {
			this.venda = new Venda();
			this.venda.setPrecoTotal(new BigDecimal("0.00"));
			ProdutoDAO produtoDAO = new ProdutoDAO();
			this.produtos = produtoDAO.listar("descricao");
			this.itensVenda = new ArrayList<>();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de vendas");
			e.printStackTrace();
		}
	}

	public void adicionar(ActionEvent event) {
		Produto produto = (Produto) event.getComponent().getAttributes().get("produto");
		int achou = -1;
		for (int posicao = 0; posicao < this.itensVenda.size(); posicao++) {
			if (this.itensVenda.get(posicao).getProduto().equals(produto)) {
				achou = posicao;
			}
		}
		if (achou < 0) {
			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setPrecoParcial(produto.getPreco());
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(Short.valueOf("1"));
			this.itensVenda.add(itemVenda);
		} else {
			ItemVenda itemVenda = this.itensVenda.get(achou);
			itemVenda.setQuantidade((short) (itemVenda.getQuantidade() + 1));
			itemVenda.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
		}
		this.calcular();
	}

	public void remover(ActionEvent evento) {
		ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("item");
		int achou = -1;
		for (int posicao = 0; posicao < this.itensVenda.size(); posicao++) {
			if (this.itensVenda.get(posicao).getProduto().equals(itemVenda.getProduto())) {
				achou = posicao;
			}
		}
		if (achou > -1) {
			this.itensVenda.remove(achou);
		}
		this.calcular();
	}

	public void calcular() {
		this.venda.setPrecoTotal(new BigDecimal("0.00"));
		for (int posicao = 0; posicao < this.itensVenda.size(); posicao++) {
			ItemVenda itemVenda = this.itensVenda.get(posicao);
			this.venda.setPrecoTotal(this.venda.getPrecoTotal().add(itemVenda.getPrecoParcial()));
		}
	}

	public void finalizar() {
		try {
			this.venda.setHorario(new Date());
			this.venda.setCliente(null);
			this.venda.setFuncionario(null);
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			this.funcionarios = funcionarioDAO.listarOrdenado();
			ClienteDAO clienteDAO = new ClienteDAO();
			this.clientes = clienteDAO.listarOrdenado();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar finalizar a venda");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			if (this.venda.getPrecoTotal().signum() == 0) {
				Messages.addGlobalError("Informe pelo menos um item para a venda");
				return;
			}
			VendaDAO vendaDAO = new VendaDAO();
			vendaDAO.salvar(this.venda, this.itensVenda);
			this.novo();
			Messages.addGlobalInfo("Venda realizada com sucesso");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a venda");
			e.printStackTrace();
		}
	}

	public void listar() {
		VendaDAO dao = new VendaDAO();
		this.vendas = dao.listar("horario");
	}

	public void atualizarPrecoParcial(ItemVenda itemVenda) {
		itemVenda
				.setPrecoParcial(itemVenda.getProduto().getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
		this.calcular();
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}
}
