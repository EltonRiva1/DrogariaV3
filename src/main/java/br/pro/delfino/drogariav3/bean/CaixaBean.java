package br.pro.delfino.drogariav3.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import br.pro.delfino.drogariav3.dao.CaixaDAO;
import br.pro.delfino.drogariav3.dao.FuncionarioDAO;
import br.pro.delfino.drogariav3.domain.Caixa;
import br.pro.delfino.drogariav3.domain.Funcionario;

@ManagedBean
@ViewScoped
public class CaixaBean {
	private ScheduleModel model;
	private Caixa caixa;
	private List<Funcionario> funcionarios;

	@PostConstruct
	public void listar() {
		this.model = new DefaultScheduleModel();
	}

	public void novo(SelectEvent event) {
		this.caixa = new Caixa();
		FuncionarioDAO dao = new FuncionarioDAO();
		this.funcionarios = dao.listar();
		this.caixa.setDataAbertura((Date) event.getObject());
	}

	public void salvar() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.caixa.getDataAbertura());
		calendar.add(Calendar.DATE, 1);
		this.caixa.setDataAbertura(calendar.getTime());
		CaixaDAO caixaDAO = new CaixaDAO();
		caixaDAO.salvar(this.caixa);
		Messages.addGlobalInfo("Caixa aberto com sucesso");
	}

	public ScheduleModel getModel() {
		return model;
	}

	public void setModel(ScheduleModel model) {
		this.model = model;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
}
