package br.pro.delfino.drogariav3.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogariav3.domain.Estado;

public class EstadoDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("Rio Grande do Sul");
		estado.setSigla("RS");
		EstadoDAO dao = new EstadoDAO();
		dao.salvar(estado);
	}

	@Test
	@Ignore
	public void listar() {
		EstadoDAO dao = new EstadoDAO();
		List<Estado> list = dao.listar();
		System.out.println("Total de registros encontrados: " + list.size());
		for (Estado estado : list) {
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 3L;
		EstadoDAO dao = new EstadoDAO();
		Estado estado = dao.buscar(codigo);
		if (estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro encontrado:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 5L;
		EstadoDAO dao = new EstadoDAO();
		Estado estado = dao.buscar(codigo);
		if (estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			dao.excluir(estado);
			System.out.println("Registro removido:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}

	@Test
	@Ignore
	public void editar() {
		Long codigo = 6L;
		EstadoDAO dao = new EstadoDAO();
		Estado estado = dao.buscar(codigo);
		if (estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro editado - Antes:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
			estado.setSigla("SC");
			estado.setNome("Santa Catarina");
			dao.editar(estado);
			System.out.println("Registro editado - Depois:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}
}
