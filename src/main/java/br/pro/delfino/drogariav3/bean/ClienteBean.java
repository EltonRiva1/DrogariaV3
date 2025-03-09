package br.pro.delfino.drogariav3.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.delfino.drogariav3.dao.ClienteDAO;
import br.pro.delfino.drogariav3.dao.PessoaDAO;
import br.pro.delfino.drogariav3.domain.Cliente;
import br.pro.delfino.drogariav3.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {
	private List<Cliente> clientes;
	private Cliente cliente;
	private List<Pessoa> pessoas;

	@PostConstruct
	public void listar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			this.clientes = clienteDAO.listar("dataCadastro");
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os clientes");
			e.printStackTrace();
		}
	}

	public void novo() {
		try {
			this.cliente = new Cliente();
			PessoaDAO dao = new PessoaDAO();
			this.pessoas = dao.listar("nome");
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo cliente");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.merge(this.cliente);
			this.novo();
			this.clientes = clienteDAO.listar("dataCadastro");
			Messages.addGlobalInfo("Cliente salvo com sucesso");
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o cliente");
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent actionEvent) {
		try {
			this.cliente = (Cliente) actionEvent.getComponent().getAttributes().get("cliente");
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.excluir(this.cliente);
			this.clientes = clienteDAO.listar();
			Messages.addGlobalInfo("Cliente removido com sucesso");
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar remover o cliente");
			e.printStackTrace();
		}
	}

	public void editar(ActionEvent actionEvent) {
		try {
			this.cliente = (Cliente) actionEvent.getComponent().getAttributes().get("cliente");
			PessoaDAO pessoaDAO = new PessoaDAO();
			this.pessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um cliente");
			e.printStackTrace();
		}
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
}
