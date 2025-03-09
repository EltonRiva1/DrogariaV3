package br.pro.delfino.drogariav3.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.delfino.drogariav3.dao.CidadeDAO;
import br.pro.delfino.drogariav3.dao.EstadoDAO;
import br.pro.delfino.drogariav3.domain.Cidade;
import br.pro.delfino.drogariav3.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {
	private List<Cidade> cidades;
	private Cidade cidade;
	private List<Estado> estados;

	@PostConstruct
	public void listar() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			this.cidades = cidadeDAO.listar();
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as cidades");
			e.printStackTrace();
		}
	}

	public void novo() {
		try {
			this.cidade = new Cidade();
			EstadoDAO dao = new EstadoDAO();
			this.estados = dao.listar("nome");
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar uma nova cidade");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.merge(this.cidade);
			this.cidade = new Cidade();
			EstadoDAO dao = new EstadoDAO();
			this.estados = dao.listar();
			this.cidades = cidadeDAO.listar();
			Messages.addGlobalInfo("Cidade salva com sucesso");
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova cidade");
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent actionEvent) {
		try {
			this.cidade = (Cidade) actionEvent.getComponent().getAttributes().get("cidade");
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.excluir(this.cidade);
			this.cidades = cidadeDAO.listar();
			Messages.addGlobalInfo("Cidade removida com sucesso");
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar remover a cidade");
			e.printStackTrace();
		}
	}

	public void editar(ActionEvent actionEvent) {
		try {
			this.cidade = (Cidade) actionEvent.getComponent().getAttributes().get("cidade");
			EstadoDAO dao = new EstadoDAO();
			this.estados = dao.listar();
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma cidade");
			e.printStackTrace();
		}
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
}
