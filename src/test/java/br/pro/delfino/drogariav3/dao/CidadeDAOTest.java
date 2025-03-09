package br.pro.delfino.drogariav3.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogariav3.domain.Cidade;
import br.pro.delfino.drogariav3.domain.Estado;

public class CidadeDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Long codigo = 2L;
		EstadoDAO dao = new EstadoDAO();
		Estado estado = dao.buscar(codigo);
		Cidade cidade = new Cidade();
		cidade.setNome("Rio de Janeiro");
		cidade.setEstado(estado);
		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);
	}

	@Test
	@Ignore
	public void listar() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> cidades = cidadeDAO.listar();
		for (Cidade cidade : cidades) {
			System.out.println("Código da cidade: " + cidade.getCodigo());
			System.out.println("Nome da cidade: " + cidade.getNome());
			System.out.println("Código do estado: " + cidade.getEstado().getCodigo());
			System.out.println("Sigla do estado: " + cidade.getEstado().getSigla());
			System.out.println("Nome do estado: " + cidade.getEstado().getNome());
			System.out.println();
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 3L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
		System.out.println("Código da cidade: " + cidade.getCodigo());
		System.out.println("Nome da cidade: " + cidade.getNome());
		System.out.println("Código do estado: " + cidade.getEstado().getCodigo());
		System.out.println("Sigla do estado: " + cidade.getEstado().getSigla());
		System.out.println("Nome do estado: " + cidade.getEstado().getNome());

	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 7L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
		cidadeDAO.excluir(cidade);
		System.out.println("Cidade Removida");
		System.out.println("Código da Cidade: " + cidade.getCodigo());
		System.out.println("Nome da Cidade: " + cidade.getNome());
		System.out.println("Código do Estado: " + cidade.getEstado().getCodigo());
		System.out.println("Sigla do Estado: " + cidade.getEstado().getSigla());
		System.out.println("Nome do Estado: " + cidade.getEstado().getNome());
	}

	@Test
	@Ignore
	public void editar() {
		Long codigoCidade = 6L;
		Long codigoEstado = 7L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigoEstado);
		System.out.println("Código do Estado: " + estado.getCodigo());
		System.out.println("Sigla do Estado: " + estado.getSigla());
		System.out.println("Nome do Estado: " + estado.getNome());
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigoCidade);
		System.out.println("Cidade A Ser Editada");
		System.out.println("Código da Cidade: " + cidade.getCodigo());
		System.out.println("Nome da Cidade: " + cidade.getNome());
		System.out.println("Código do Estado: " + cidade.getEstado().getCodigo());
		System.out.println("Sigla do Estado: " + cidade.getEstado().getSigla());
		System.out.println("Nome do Estado: " + cidade.getEstado().getNome());
		cidade.setNome("Guarapuava");
		cidade.setEstado(estado);
		cidadeDAO.editar(cidade);
		System.out.println("Cidade Editada");
		System.out.println("Código da Cidade: " + cidade.getCodigo());
		System.out.println("Nome da Cidade: " + cidade.getNome());
		System.out.println("Código do Estado: " + cidade.getEstado().getCodigo());
		System.out.println("Sigla do Estado: " + cidade.getEstado().getSigla());
		System.out.println("Nome do Estado: " + cidade.getEstado().getNome());
	}

	@Test
	public void buscarPorEstado() {
		Long estadoCodigo = 1L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> cidades = cidadeDAO.buscarPorEstado(estadoCodigo);
		for (Cidade cidade : cidades) {
			System.out.println("Código da cidade: " + cidade.getCodigo());
			System.out.println("Nome da cidade: " + cidade.getNome());
			System.out.println("Código do estado: " + cidade.getEstado().getCodigo());
			System.out.println("Sigla do estado: " + cidade.getEstado().getSigla());
			System.out.println("Nome do estado: " + cidade.getEstado().getNome());
			System.out.println();
		}
	}
}
