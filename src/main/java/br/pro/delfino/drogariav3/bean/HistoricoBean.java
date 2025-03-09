package br.pro.delfino.drogariav3.bean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.pro.delfino.drogariav3.dao.HistoricoDAO;
import br.pro.delfino.drogariav3.dao.ProdutoDAO;
import br.pro.delfino.drogariav3.domain.Historico;
import br.pro.delfino.drogariav3.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class HistoricoBean implements Serializable {
	private Produto produto;
	private Boolean displaysDataPanel;
	private Historico historico;

	@PostConstruct
	public void novo() {
		this.produto = new Produto();
		this.displaysDataPanel = false;
		this.historico = new Historico();
	}

	public void buscar() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			Produto produto = dao.buscar(this.produto.getCodigo());
			if (produto == null) {
				this.displaysDataPanel = false;
				Messages.addGlobalWarn("Não existe produto cadastrado para o código informado");
			} else {
				this.displaysDataPanel = true;
				this.produto = produto;
			}
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar buscar o produto");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			this.historico.setHorario(new Date());
			this.historico.setProduto(this.produto);
			HistoricoDAO dao = new HistoricoDAO();
			dao.salvar(this.historico);
			Messages.addGlobalInfo("Histórico salvo com sucesso");
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o histórico");
			e.printStackTrace();
		}
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Boolean getDisplaysDataPanel() {
		return displaysDataPanel;
	}

	public void setDisplaysDataPanel(Boolean displaysDataPanel) {
		this.displaysDataPanel = displaysDataPanel;
	}

	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}
}
