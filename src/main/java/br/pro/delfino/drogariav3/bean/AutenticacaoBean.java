package br.pro.delfino.drogariav3.bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import br.pro.delfino.drogariav3.dao.UsuarioDAO;
import br.pro.delfino.drogariav3.domain.Pessoa;
import br.pro.delfino.drogariav3.domain.Usuario;
import br.pro.delfino.drogariav3.enumeracao.TipoUsuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Usuario usuario, usuarioAutenticado;

	@PostConstruct
	public void iniciar() {
		this.usuario = new Usuario();
		this.usuario.setPessoa(new Pessoa());
	}

	public void autenticar() {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			this.usuarioAutenticado = dao.autenticar(this.usuario.getPessoa().getCpf(), this.usuario.getSenha());
			if (this.usuarioAutenticado == null) {
				Messages.addGlobalError("CPF e/ou senha incorretos");
				return;
			}
			Faces.redirect("./pages/principal.xhtml");
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		}
	}

	public boolean havePermissions(List<String> permissions) {
		if (this.usuarioAutenticado == null || this.usuarioAutenticado.getTipoUsuario() == null) {
			return false;
		}
		for (String permission : permissions) {
			try {
				TipoUsuario tipo = TipoUsuario.valueOf(permission);
				if (this.usuarioAutenticado.getTipoUsuario() == tipo) {
					return true;
				}
			} catch (IllegalArgumentException e) {
				// TODO: handle exception
			}
		}
		return false;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioAutenticado() {
		return usuarioAutenticado;
	}

	public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}
}
