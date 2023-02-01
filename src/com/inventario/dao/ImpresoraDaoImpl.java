package com.inventario.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.inventario.model.Impresora;
import com.inventario.model.Personal;
import com.inventario.model.Teclado;

public class ImpresoraDaoImpl implements ImpresoraDao, Serializable {

	private static final long serialVersionUID = -7257802002805615261L;
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void altaImpre(Impresora impre) {
		sessionFactory.getCurrentSession().save(impre);
	}

	@Override
	public void modificarImpre(Impresora impre1) {
		sessionFactory.getCurrentSession().saveOrUpdate(impre1);
	}
	
	@Override
	public void borrarImpre(Impresora impre) {
		sessionFactory.getCurrentSession().delete(impre);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Impresora> getImpresora() {
		return sessionFactory.getCurrentSession().createCriteria(Impresora.class)
				.createAlias("fabricante", "fab").addOrder(Order.asc("id_impresora"))
				.list();
	}

	//////Teclado
	
	@Override
	public void altaTecla(Teclado tecla) {
		sessionFactory.getCurrentSession().save(tecla);
	}

	@Override
	public void modificarTecla(Teclado tecla1) {
		sessionFactory.getCurrentSession().saveOrUpdate(tecla1);
	}
	
	@Override
	public void borrarTecla(Teclado tecla) {
		sessionFactory.getCurrentSession().delete(tecla);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Teclado> getTeclado() {
		return sessionFactory.getCurrentSession().createCriteria(Teclado.class)
				.createAlias("fabricante", "fab").addOrder(Order.asc("id_teclado"))
				.list();
	}

	
	
}