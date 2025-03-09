package br.pro.delfino.drogariav3.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.pro.delfino.drogariav3.bean.AutenticacaoBean;
import br.pro.delfino.drogariav3.domain.Usuario;

@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		String currentPage = Faces.getViewId();
		boolean itsAnAuthenticationPage = currentPage.contains("autenticacao.xhtml");
		if (!itsAnAuthenticationPage) {
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			if (autenticacaoBean == null) {
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
			Usuario usuario = autenticacaoBean.getUsuarioAutenticado();
			if (usuario == null) {
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}

}
