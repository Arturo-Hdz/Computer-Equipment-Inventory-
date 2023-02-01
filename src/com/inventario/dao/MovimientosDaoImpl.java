package com.inventario.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.inventario.model.Equipo;
import com.inventario.model.Area;
import com.inventario.model.Departamento;
import com.inventario.model.Movimientos;

public class MovimientosDaoImpl implements MovimientosDao, Serializable {

	private static final long serialVersionUID = 673024667757288725L;
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void altaArea(Area area) {
		sessionFactory.getCurrentSession().save(area);
	}

	@Override
	public void modificarArea(Area area1) {
		sessionFactory.getCurrentSession().saveOrUpdate(area1);
	}

	@Override
	public void borrarArea(Area area) {
		sessionFactory.getCurrentSession().delete(area);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Area> getArea() {
		return sessionFactory.getCurrentSession().createCriteria(Area.class)
				.addOrder(Order.asc("id_area")).list();
	}

	// //////Departamento

	@Override
	public void altaDepa(Departamento depa) {
		sessionFactory.getCurrentSession().save(depa);
	}

	@Override
	public void modificarDepa(Departamento depa1) {
		sessionFactory.getCurrentSession().saveOrUpdate(depa1);
	}

	@Override
	public void borrarDepa(Departamento depa) {
		sessionFactory.getCurrentSession().delete(depa);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Departamento> getDepartamento() {
		return sessionFactory.getCurrentSession()
				.createCriteria(Departamento.class)
				.addOrder(Order.asc("id_departamento")).list();
	}

	// //////Movimientos

	@Override
	public void altaMov(Movimientos mov) {
		sessionFactory.getCurrentSession().save(mov);
	}

	@Override
	public void modificarMovimiento(Movimientos mov1) {
		sessionFactory.getCurrentSession().update(mov1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movimientos> getMovimiento() {
		return sessionFactory.getCurrentSession()
				.createCriteria(Movimientos.class)
				.createAlias("equipo", "eq")
				.createAlias("area", "are").
				createAlias("departamento", "depa")
				.createAlias("personal", "per")
				.addOrder(Order.asc("id_movimientos")).list();
	}

	@Override
	public Equipo getEqByKey(String id) throws Exception {
		return (Equipo) sessionFactory.getCurrentSession()
				.createCriteria(Equipo.class)
				.add(Restrictions.eq("id_equipo", Integer.parseInt(id)))
				.uniqueResult();
	}

	@Override
	public Area getAreaByKey(String id) throws Exception {
		return (Area) sessionFactory.getCurrentSession()
				.createCriteria(Area.class)
				.add(Restrictions.eq("id_area", Integer.parseInt(id)))
				.uniqueResult();
	}

	@Override
	public Departamento getDepaByKey(String id) throws Exception {
		return (Departamento) sessionFactory.getCurrentSession()
				.createCriteria(Departamento.class)
				.add(Restrictions.eq("id_departamento", Integer.parseInt(id)))
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Equipo> getEquipos() throws Exception {
		return sessionFactory.getCurrentSession().createCriteria(Equipo.class)
				.addOrder(Order.asc("id_equipo")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Departamento> getDepartamentos() throws Exception {
		return sessionFactory.getCurrentSession()
				.createCriteria(Departamento.class)
				.addOrder(Order.asc("id_departamento")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Area> getAreas() throws Exception {
		return sessionFactory.getCurrentSession().createCriteria(Area.class)
				.addOrder(Order.asc("id_area")).list();
	}

}