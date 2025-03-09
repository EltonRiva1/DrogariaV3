package br.pro.delfino.drogariav3.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.pro.delfino.drogariav3.domain.Usuario;
import br.pro.delfino.drogariav3.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario> {
	public Usuario autenticar(String cpf, String senha) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(Usuario.class);
			criteria.createAlias("pessoa", "p");
			SimpleHash hash = new SimpleHash("md5", senha);
			criteria.add(Restrictions.eq("p.cpf", cpf)).add(Restrictions.eq("senha", hash.toHex()));
			Usuario usuario = (Usuario) criteria.uniqueResult();
			return usuario;
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		} finally {
			session.close();
		}
	}
}
