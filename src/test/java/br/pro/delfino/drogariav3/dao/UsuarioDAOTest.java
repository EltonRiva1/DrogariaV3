package br.pro.delfino.drogariav3.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogariav3.domain.Pessoa;
import br.pro.delfino.drogariav3.domain.Usuario;
import br.pro.delfino.drogariav3.enumeracao.TipoUsuario;

public class UsuarioDAOTest {
	@Test
	public void salvar() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(3L);
		System.out.println("Pessoa Encontrada");
		System.out.println("Nome: " + pessoa.getNome());
		System.out.println("CPF: " + pessoa.getCpf());
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setPessoa(pessoa);
		usuario.setSenhaSemCriptografia("q1w2e3r4");
		SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
		usuario.setSenha(hash.toHex());
		usuario.setTipoUsuario(TipoUsuario.GERENTE);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
		System.out.println("Usuário salvo com sucesso.");
	}

	@Test
	@Ignore
	public void autenticar() {
		String cpf = "123.456.789-00", senha = "q1w2e3r4";
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.autenticar(cpf, senha);
		System.out.println("Usuário autenticado: " + usuario);
	}
}
