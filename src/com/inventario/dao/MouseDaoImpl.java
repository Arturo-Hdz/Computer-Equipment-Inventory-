package com.inventario.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.inventario.model.Impresora;
import com.inventario.model.Monitor;
import com.inventario.model.Mouse;

public class MouseDaoImpl implements MouseDao, Serializable {

	private static final long serialVersionUID = 1938930418064691327L;
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void altaMouse(Mouse mouse) {
		sessionFactory.getCurrentSession().save(mouse);
	}

	@Override
	public void modificarMouse(Mouse mou1) {
		sessionFactory.getCurrentSession().saveOrUpdate(mou1);
	}
	
	@Override
	public void borrarMouse(Mouse mou) {
		sessionFactory.getCurrentSession().delete(mou);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Mouse> getMouse() {
		return sessionFactory.getCurrentSession().createCriteria(Mouse.class)
				.createAlias("fabricante", "fab").addOrder(Order.asc("id_mouse"))
				.list();
	}

	//////Monitor
	
	@Override
	public void altaMon(Monitor mon) {
		sessionFactory.getCurrentSession().save(mon);
	}

	@Override
	public void modificarMon(Monitor mon1) {
		sessionFactory.getCurrentSession().saveOrUpdate(mon1);
	}
	
	@Override
	public void borrarMon(Monitor mon) {
		sessionFactory.getCurrentSession().delete(mon);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Monitor> getMonitor() {
		return sessionFactory.getCurrentSession().createCriteria(Monitor.class)
				.createAlias("fabricante", "fab").addOrder(Order.asc("id_monitor"))
				.list();
	}

	
	
}