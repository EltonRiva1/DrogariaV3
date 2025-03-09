package br.pro.delfino.drogariav3.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.pro.delfino.drogariav3.domain.ItemVenda;
import br.pro.delfino.drogariav3.domain.Produto;
import br.pro.delfino.drogariav3.domain.Venda;
import br.pro.delfino.drogariav3.util.HibernateUtil;

public class VendaDAO extends GenericDAO<Venda> {

	public void salvar(Venda venda, List<ItemVenda> itensVenda) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(venda);
			for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
				ItemVenda itemVenda = itensVenda.get(posicao);
				itemVenda.setVenda(venda);
				session.save(itemVenda);
				Produto produto = itemVenda.getProduto();
				int quantidade = produto.getQuantidade() - itemVenda.getQuantidade();
				if (quantidade >= 0) {
					produto.setQuantidade((short) quantidade);
					session.update(produto);
				} else {
					throw new RuntimeException("Quantidade insuficiente em estoque");
				}
			}
			transaction.commit();
		} catch (RuntimeException e) {
			// TODO: handle exception
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}

}
