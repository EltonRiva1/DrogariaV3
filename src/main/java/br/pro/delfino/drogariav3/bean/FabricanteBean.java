package br.pro.delfino.drogariav3.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import br.pro.delfino.drogariav3.domain.Fabricante;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FabricanteBean implements Serializable {
	private Fabricante fabricante;
	private List<Fabricante> fabricantes;

	@PostConstruct
	public void listar() {
		try {
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/DrogariaV3/rest/fabricante");
			String json = target.request().get(String.class);
			Gson gson = new Gson();
			Fabricante[] fabricantes = gson.fromJson(json, Fabricante[].class);
			this.fabricantes = Arrays.asList(fabricantes);
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os fabricantes");
			e.printStackTrace();
		}
	}

	public void novo() {
		this.fabricante = new Fabricante();
	}

	public void salvar() {
		try {
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/DrogariaV3/rest/fabricante");
			Gson gson = new Gson();
			String json = gson.toJson(this.fabricante);
			target.request().post(Entity.json(json));
			this.novo();
			json = target.request().get(String.class);
			Fabricante[] fabricantes = gson.fromJson(json, Fabricante[].class);
			this.fabricantes = Arrays.asList(fabricantes);
			Messages.addGlobalInfo("Fabricante salvo com sucesso");
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o fabricante");
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent event) {
		try {
			this.fabricante = (Fabricante) event.getComponent().getAttributes().get("fabricante");
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/DrogariaV3/rest/fabricante"),
					targetDelete = target.path("{codigo}").resolveTemplate("codigo", this.fabricante.getCodigo());
			targetDelete.request().delete();
			String json = target.request().get(String.class);
			Gson gson = new Gson();
			Fabricante[] fabricantes = gson.fromJson(json, Fabricante[].class);
			this.fabricantes = Arrays.asList(fabricantes);
			Messages.addGlobalInfo("Fabricante removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o fabricante");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent event) {
		this.fabricante = (Fabricante) event.getComponent().getAttributes().get("fabricante");
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
}
