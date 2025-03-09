package br.pro.delfino.drogariav3.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("drogariav3")
public class DrogariaV3Service {
	@GET
	public String exibir() {
		return "Curso de Java";
	}
}
