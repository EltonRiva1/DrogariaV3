package br.pro.delfino.drogariav3.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.delfino.drogariav3.dao.EstadoDAO;
import br.pro.delfino.drogariav3.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {
	private Estado estado;
	private List<Estado> estados;

	public void salvar() {
		try {
			EstadoDAO dao = new EstadoDAO();
			dao.merge(this.estado);
			this.novo();
			this.estados = dao.listar();
			Messages.addGlobalInfo("Estado salvo com sucesso");
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o estado");
			e.printStackTrace();
		}
	}

	public void novo() {
		this.estado = new Estado();
	}

	@PostConstruct
	public void listar() {
		try {
			EstadoDAO dao = new EstadoDAO();
			this.estados = dao.listar();
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent actionEvent) {
		try {
			this.estado = (Estado) actionEvent.getComponent().getAttributes().get("estado");
			EstadoDAO dao = new EstadoDAO();
			dao.excluir(this.estado);
			this.estados = dao.listar();
			Messages.addGlobalInfo("Estado removido com sucesso");
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar remover o estado");
			e.printStackTrace();
		}
	}

	public void editar(ActionEvent actionEvent) {
		this.estado = (Estado) actionEvent.getComponent().getAttributes().get("estado");
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
}
