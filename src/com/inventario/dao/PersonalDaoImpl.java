package com.inventario.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.inventario.model.Personal;

public class PersonalDaoImpl implements PersonalDao, Serializable {

	private static final long serialVersionUID = -3426253595523994478L;
	
	@Autowired
	SessionFactory sessionFactory;
	
	// ////Personal

	@Override
	public void altaPersonal(Personal person) {
		sessionFactory.getCurrentSession().save(person);
	}

	@Override
	public void modificarPersonal(Personal person1) {
		sessionFactory.getCurrentSession().saveOrUpdate(person1);
	}

	@Override
	public void borrarPersonal(Personal person) {
		sessionFactory.getCurrentSession().delete(person);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Personal> getPersonal() {
		return sessionFactory.getCurrentSession()
				.createCriteria(Personal.class)
				.add(Restrictions.eq("estatus", 1))
				.addOrder(Order.asc("id_personal"))
				.list();
	}
	
}