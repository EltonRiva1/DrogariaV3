package br.pro.delfino.drogariav3.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.pro.delfino.drogariav3.util.HibernateUtil;

public class GenericDAO<T> {
	private Class<T> t;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.t = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void salvar(T t) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(t);
			transaction.commit();
		} catch (RuntimeException e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> listar() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(this.t);
			List<T> list = criteria.list();
			return list;
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public T buscar(Long codigo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(this.t);
			criteria.add(Restrictions.idEq(codigo));
			T t = (T) criteria.uniqueResult();
			return t;
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		} finally {
			session.close();
		}
	}

	public void excluir(T t) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(t);
			transaction.commit();
		} catch (RuntimeException e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public void editar(T t) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(t);
			transaction.commit();
		} catch (RuntimeException e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public T merge(T t) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			T entity = (T) session.merge(t);
			transaction.commit();
			return entity;
		} catch (RuntimeException e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> listar(String campoOrdenacao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(this.t);
			criteria.addOrder(Order.asc(campoOrdenacao));
			List<T> list = criteria.list();
			return list;
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		} finally {
			session.close();
		}
	}
}
