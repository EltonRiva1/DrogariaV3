package br.pro.delfino.drogariav3.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.pro.delfino.drogariav3.domain.Cliente;
import br.pro.delfino.drogariav3.util.HibernateUtil;

public class ClienteDAO extends GenericDAO<Cliente> {

	@SuppressWarnings("unchecked")
	public List<Cliente> listarOrdenado() {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(Cliente.class);
			criteria.createAlias("pessoa", "p");
			criteria.addOrder(Order.asc("p.nome"));
			List<Cliente> clientes = criteria.list();
			return clientes;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			session.close();
		}
	}
}
