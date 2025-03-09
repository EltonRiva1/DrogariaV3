package br.pro.delfino.drogariav3.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.pro.delfino.drogariav3.domain.Cidade;
import br.pro.delfino.drogariav3.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade> {
	@SuppressWarnings("unchecked")
	public List<Cidade> buscarPorEstado(Long estadoCodigo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(Cidade.class);
			criteria.add(Restrictions.eq("estado.codigo", estadoCodigo));
			criteria.addOrder(Order.asc("nome"));
			List<Cidade> cidades = criteria.list();
			return cidades;
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		} finally {
			session.close();
		}
	}
}