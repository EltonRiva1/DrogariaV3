package br.pro.delfino.drogariav3.dao;

import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogariav3.domain.Caixa;

public class CaixaDAOTest {
	@Test
	@Ignore
	public void salvar() throws ParseException {
		Caixa caixa = new Caixa();
		caixa.setDataAbertura(new SimpleDateFormat("dd/MM/yyyy").parse("25/01/2025"));
		caixa.setValorAbertura(new BigDecimal("100.00"));
		CaixaDAO caixaDAO = new CaixaDAO();
		caixaDAO.salvar(caixa);
	}

	@Test
	@Ignore
	public void buscar() throws ParseException {
		CaixaDAO caixaDAO = new CaixaDAO();
		Caixa caixa = caixaDAO.buscar(new SimpleDateFormat("dd/MM/yyyy").parse("23/01/2025"));
		assertNull(caixa);
	}
}
