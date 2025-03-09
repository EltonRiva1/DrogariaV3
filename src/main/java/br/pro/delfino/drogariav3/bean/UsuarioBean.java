package br.pro.delfino.drogariav3.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.pro.delfino.drogariav3.dao.PessoaDAO;
import br.pro.delfino.drogariav3.dao.UsuarioDAO;
import br.pro.delfino.drogariav3.domain.Pessoa;
import br.pro.delfino.drogariav3.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {
	private List<Usuario> usuarios;
	private Usuario usuario;
	private List<Pessoa> pessoas;

	@PostConstruct
	public void listar() {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			this.usuarios = dao.listar("tipoUsuario");
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuários");
			e.printStackTrace();
		}
	}

	public void novo() {
		try {
			this.usuario = new Usuario();
			PessoaDAO dao = new PessoaDAO();
			this.pessoas = dao.listar("nome");
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo usuário");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			dao.merge(this.usuario);
			this.usuario = new Usuario();
			this.usuarios = dao.listar("tipoUsuario");
			PessoaDAO pessoaDAO = new PessoaDAO();
			this.pessoas = pessoaDAO.listar("nome");
			Messages.addGlobalInfo(Faces.getResourceBundle("mensagens").getString("usuarioSalvo"));
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o usuário");
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent actionEvent) {
		try {
			this.usuario = (Usuario) actionEvent.getComponent().getAttributes().get("usuario");
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(this.usuario);
			this.usuarios = usuarioDAO.listar();
			Messages.addGlobalInfo("Usuário removido com sucesso");
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addGlobalError("Ocorreu um erro ao tentar remover o usuário");
			e.printStackTrace();
		}
	}

	public void editar(ActionEvent actionEvent) {
		try {
			this.usuario = (Usuario) actionEvent.getComponent().getAttributes().get("usuario");
			PessoaDAO pessoaDAO = new PessoaDAO();
			this.pessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException e) {
			// TODO: handle exception
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um usuário");
			e.printStackTrace();
		}
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
}
