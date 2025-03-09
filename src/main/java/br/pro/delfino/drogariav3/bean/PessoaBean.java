package br.pro.delfino.drogariav3.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import br.pro.delfino.drogariav3.dao.CidadeDAO;
import br.pro.delfino.drogariav3.dao.EstadoDAO;
import br.pro.delfino.drogariav3.dao.PessoaDAO;
import br.pro.delfino.drogariav3.domain.Cidade;
import br.pro.delfino.drogariav3.domain.Estado;
import br.pro.delfino.drogariav3.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	private List<Estado> estados;
	private List<Cidade> cidades;
	private Estado estado;

	@PostConstruct
	public void listar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			this.pessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as pessoas");
			e.printStackTrace();
		}
	}

	public void novo() {
		try {
			this.pessoa = new Pessoa();
			this.estado = new Estado();
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/DrogariaV3/rest/estado");
			String json = target.request().get(String.class);
			Gson gson = new Gson();
			Estado[] estados = gson.fromJson(json, Estado[].class);
			this.estados = Arrays.asList(estados);
			this.cidades = new ArrayList<Cidade>();
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar uma nova pessoa");
			e.printStackTrace();
		}
	}

	public void editar(ActionEvent actionEvent) {
		try {
			this.pessoa = (Pessoa) actionEvent.getComponent().getAttributes().get("pessoa");
			this.estado = this.pessoa.getCidade().getEstado();
			EstadoDAO dao = new EstadoDAO();
			this.estados = dao.listar("nome");
			CidadeDAO cidadeDAO = new CidadeDAO();
			this.cidades = cidadeDAO.buscarPorEstado(this.estado.getCodigo());
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar uma pessoa");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			PessoaDAO dao = new PessoaDAO();
			dao.merge(this.pessoa);
			this.pessoas = dao.listar("nome");
			this.novo();
			Messages.addGlobalInfo("Pessoa salva com sucesso");
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a pessoa");
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent actionEvent) {

	}

	public void popular() {
		try {
			if (this.estado != null) {
				Client client = ClientBuilder.newClient();
				WebTarget target = client.target("http://localhost:8080/DrogariaV3/rest/cidade/{estadoCodigo}")
						.resolveTemplate("estadoCodigo", this.estado.getCodigo());
				String json = target.request().get(String.class);
				Gson gson = new Gson();
				Cidade[] cidades = gson.fromJson(json, Cidade[].class);
				this.cidades = Arrays.asList(cidades);
			} else {
				this.cidades = new ArrayList<Cidade>();
			}
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as cidades");
			e.printStackTrace();
		}
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
