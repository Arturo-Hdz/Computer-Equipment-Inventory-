package com.inventario.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.inventario.model.Equipo;
import com.inventario.model.Paqueteria;
import com.inventario.model.Tipomantenimiento;

public class PaqueteriaDaoImpl implements PaqueteriaDao, Serializable {

	private static final long serialVersionUID = 5213924722376087523L;

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void altaPaqueteria(Paqueteria pq) {
		sessionFactory.getCurrentSession().save(pq);
	}

	@Override
	public void modificarPaqueteria(Paqueteria pq1) {
		sessionFactory.getCurrentSession().saveOrUpdate(pq1);
	}

	@Override
	public void borrarPaqueteria(Paqueteria pq) {
		sessionFactory.getCurrentSession().delete(pq);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Paqueteria> getPaqueteria() {
		return sessionFactory.getCurrentSession()
				.createCriteria(Paqueteria.class)
				.addOrder(Order.asc("id_paqueteria")).list();
	}

	// ///////Tipo mantenimiento

	@Override
	public void altaTipoMantenimiento(Tipomantenimiento tm) {
		sessionFactory.getCurrentSession().save(tm);
	}

	@Override
	public void modificarTipoMantenimiento(Tipomantenimiento tm1) {
		sessionFactory.getCurrentSession().saveOrUpdate(tm1);
	}

	@Override
	public void borrarTipoMantenimiento(Tipomantenimiento tm) {
		sessionFactory.getCurrentSession().delete(tm);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tipomantenimiento> getTipoMantenimiento() {
		return sessionFactory.getCurrentSession()
				.createCriteria(Tipomantenimiento.class)
				.createAlias("equipo", "equi")
				.add(Restrictions.eq("estatus", 1))
				.addOrder(Order.asc("id_tipo_mantenimiento")).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tipomantenimiento> getTipoMantenimientob() {
		return sessionFactory.getCurrentSession()
				.createCriteria(Tipomantenimiento.class)
				.createAlias("equipo", "equi")
				.add(Restrictions.eq("estatus", 0))
				.addOrder(Order.asc("id_tipo_mantenimiento")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Equipo> getEquipos() {
		return sessionFactory.getCurrentSession().createCriteria(Equipo.class)
				.add(Restrictions.eq("estatus", 1))
				.addOrder(Order.asc("id_equipo")).list();

	}

	@Override
	public Equipo getEqByKey(String id) throws Exception {
		return (Equipo) sessionFactory.getCurrentSession()
				.createCriteria(Equipo.class)
				.add(Restrictions.eq("estatus", 1))
				.add(Restrictions.eq("id_equipo", Integer.parseInt(id)))
				.uniqueResult();
	}

}