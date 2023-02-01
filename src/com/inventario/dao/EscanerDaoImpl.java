package com.inventario.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.inventario.model.Impresora;
import com.inventario.model.Scanner;
import com.inventario.model.Nobreak;

public class EscanerDaoImpl implements EscanerDao, Serializable {

	private static final long serialVersionUID = -6454785506459826161L;
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void altaEsca(Scanner esca) {
		sessionFactory.getCurrentSession().save(esca);
	}

	@Override
	public void modificarEsca(Scanner esca1) {
		sessionFactory.getCurrentSession().saveOrUpdate(esca1);
	}
	
	@Override
	public void borrarEsca(Scanner esca) {
		sessionFactory.getCurrentSession().delete(esca);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Scanner> getEscaner() {
		return sessionFactory.getCurrentSession().createCriteria(Scanner.class)
				.createAlias("fabricante", "fab").addOrder(Order.asc("id_scanner"))
				.list();
	}

	//////Nobreak
	
	@Override
	public void altaNob(Nobreak nob) {
		sessionFactory.getCurrentSession().save(nob);
	}

	@Override
	public void modificarNob(Nobreak nob1) {
		sessionFactory.getCurrentSession().saveOrUpdate(nob1);
	}
	
	@Override
	public void borrarNob(Nobreak nob) {
		sessionFactory.getCurrentSession().delete(nob);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Nobreak> getNobreak() {
		return sessionFactory.getCurrentSession().createCriteria(Nobreak.class)
				.createAlias("fabricante", "fab").addOrder(Order.asc("id_nobreak"))
				.list();
	}

	
	
}