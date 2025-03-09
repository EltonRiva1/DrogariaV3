package br.pro.delfino.drogariav3.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.pro.delfino.drogariav3.domain.Funcionario;
import br.pro.delfino.drogariav3.util.HibernateUtil;

public class FuncionarioDAO extends GenericDAO<Funcionario> {
	@SuppressWarnings("unchecked")
	public List<Funcionario> listarOrdenado() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(Funcionario.class);
			criteria.createAlias("pessoa", "p");
			criteria.addOrder(Order.asc("p.nome"));
			List<Funcionario> funcionarios = criteria.list();
			return funcionarios;
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		} finally {
			session.close();
		}
	}
}
