package com.inventario.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.inventario.model.Sistemaoperativo;

public class SoDaoImpl implements SoDao, Serializable {


	private static final long serialVersionUID = -2666269354230148214L;

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void altaSo(Sistemaoperativo so) {
		sessionFactory.getCurrentSession().save(so);
	}

	@Override
	public void modificarSo(Sistemaoperativo so) {
		sessionFactory.getCurrentSession().saveOrUpdate(so);
	}
	
	@Override
	public void borrarSo(Sistemaoperativo so) {
		sessionFactory.getCurrentSession().delete(so);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Sistemaoperativo> getSistemaoperativo() {
		return sessionFactory.getCurrentSession()
				.createCriteria(Sistemaoperativo.class).addOrder(Order.asc("id_so"))
				.list();
	}

}