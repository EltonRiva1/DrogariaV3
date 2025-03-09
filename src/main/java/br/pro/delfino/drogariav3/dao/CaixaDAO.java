package br.pro.delfino.drogariav3.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.pro.delfino.drogariav3.domain.Caixa;
import br.pro.delfino.drogariav3.util.HibernateUtil;

public class CaixaDAO extends GenericDAO<Caixa> {
	public Caixa buscar(Date date) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(Caixa.class);
			criteria.add(Restrictions.eq("dataAbertura", date));
			Caixa caixa = (Caixa) criteria.uniqueResult();
			return caixa;
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		} finally {
			session.close();
		}
	}
}
