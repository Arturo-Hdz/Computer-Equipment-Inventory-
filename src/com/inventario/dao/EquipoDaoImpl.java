package com.inventario.dao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Query;

import com.inventario.model.Equipo;
import com.inventario.model.Monitor;
import com.inventario.model.Mouse;
import com.inventario.model.Movimientos;
import com.inventario.model.Nobreak;
import com.inventario.model.Scanner;
import com.inventario.model.Teclado;
import com.inventario.model.Impresora;
import com.inventario.model.Sistemaoperativo;
import com.inventario.model.Fabricante;
import com.inventario.model.Paqueteria;
import com.inventario.model.Area;
import com.inventario.model.Departamento;
import com.inventario.model.Personal;

public class EquipoDaoImpl implements EquipoDao, Serializable {

	private static final long serialVersionUID = -1127484731772529822L;

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Equipo> getFechaequipo(Date fechaIni, Date fechaFin)
			throws Exception {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calendar = GregorianCalendar.getInstance();
		String fIni = "";
		String fFin = "";
		fechaIni = fechaIni == null ? calendar.getTime() : fechaIni;
		fechaFin = fechaFin == null ? calendar.getTime() : fechaFin;
		fIni = sdf1.format(fechaIni);
		fFin = sdf1.format(fechaFin);
		fIni += " 00:00:00";
		fFin += " 23:59:59";
		return sessionFactory
				.getCurrentSession()
				.createCriteria(Equipo.class)
				.createAlias("monitor", "mon")
				.createAlias("mouse", "mou")
				.createAlias("nobreak", "nob")
				.createAlias("scanner", "esca")
				.createAlias("teclado", "tec")
				.createAlias("impresora", "imp")
				.createAlias("sistemaoperativo", "so")
				.createAlias("fabricante", "fab")
				.createAlias("paqueteria", "paq")
				.createAlias("area", "are")
				.createAlias("departamento", "depa")
				.createAlias("personal", "per")
				.add(Restrictions.eq("estatus", 1))
				.add(Restrictions.between("fecha", sdf.parse(fIni),
						sdf.parse(fFin))).addOrder(Order.desc("id_equipo"))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Monitor> getMonitors() throws Exception {
		return sessionFactory.getCurrentSession().createCriteria(Monitor.class)
				.addOrder(Order.asc("id_monitor")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mouse> getMouses() throws Exception {
		return sessionFactory.getCurrentSession().createCriteria(Mouse.class)
				.addOrder(Order.asc("id_mouse")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Nobreak> getNobreaks() throws Exception {
		return sessionFactory.getCurrentSession().createCriteria(Nobreak.class)
				.addOrder(Order.asc("id_nobreak")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Scanner> getEscaners() throws Exception {
		return sessionFactory.getCurrentSession().createCriteria(Scanner.class)
				.addOrder(Order.asc("id_scanner")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Teclado> getTeclados() throws Exception {
		return sessionFactory.getCurrentSession().createCriteria(Teclado.class)
				.addOrder(Order.asc("id_teclado")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Impresora> getImpresoras() throws Exception {
		return sessionFactory.getCurrentSession()
				.createCriteria(Impresora.class)
				.addOrder(Order.asc("id_impresora")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sistemaoperativo> getSos() throws Exception {
		return sessionFactory.getCurrentSession()
				.createCriteria(Sistemaoperativo.class)
				.addOrder(Order.asc("id_so")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fabricante> getFabs() throws Exception {
		return sessionFactory.getCurrentSession()
				.createCriteria(Fabricante.class)
				.addOrder(Order.asc("id_fabricante")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Paqueteria> getPaqs() throws Exception {
		return sessionFactory.getCurrentSession()
				.createCriteria(Paqueteria.class)
				.addOrder(Order.asc("id_paqueteria")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Area> getArea() throws Exception {
		return sessionFactory.getCurrentSession().createCriteria(Area.class)
				.addOrder(Order.asc("id_area")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Departamento> getDepas() throws Exception {
		return sessionFactory.getCurrentSession()
				.createCriteria(Departamento.class)
				.addOrder(Order.asc("id_departamento")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Personal> getPrs() throws Exception {
		return sessionFactory.getCurrentSession()
				.createCriteria(Personal.class)
				.addOrder(Order.asc("id_personal")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Equipo> getEquipo() {
		return sessionFactory.getCurrentSession().createCriteria(Equipo.class)
				.createAlias("monitor", "mon").createAlias("mouse", "mou")
				.createAlias("nobreak", "nob").createAlias("scanner", "esca")
				.createAlias("teclado", "tec").createAlias("impresora", "imp")
				.createAlias("sistemaoperativo", "so")
				.createAlias("fabricante", "fab")
				.createAlias("paqueteria", "paq").createAlias("area", "are")
				.createAlias("departamento", "depa")
				.createAlias("personal", "per")
				.add(Restrictions.eq("estatus", 1))
				.addOrder(Order.asc("id_equipo")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Equipo> getEquipob() {
		return sessionFactory.getCurrentSession().createCriteria(Equipo.class)
				.createAlias("monitor", "mon").createAlias("mouse", "mou")
				.createAlias("nobreak", "nob").createAlias("scanner", "esca")
				.createAlias("teclado", "tec").createAlias("impresora", "imp")
				.createAlias("sistemaoperativo", "so")
				.createAlias("fabricante", "fab")
				.createAlias("paqueteria", "paq").createAlias("area", "are")
				.createAlias("departamento", "depa")
				.createAlias("personal", "per")
				.add(Restrictions.eq("estatus", 0))
				.addOrder(Order.asc("id_equipo")).list();
	}

	@Override
	public Equipo getEquipoByKey(String id) throws Exception {
		return (Equipo) sessionFactory.getCurrentSession()
				.createCriteria(Equipo.class)
				.add(Restrictions.eq("id_equipo", Integer.parseInt(id)))
				.uniqueResult();
	}

	@Override
	public Monitor getMonByKey(String id) throws Exception {
		return (Monitor) sessionFactory.getCurrentSession()
				.createCriteria(Monitor.class)
				.add(Restrictions.eq("id_monitor", Integer.parseInt(id)))
				.uniqueResult();
	}

	@Override
	public Mouse getMouByKey(String id) throws Exception {
		return (Mouse) sessionFactory.getCurrentSession()
				.createCriteria(Mouse.class)
				.add(Restrictions.eq("id_mouse", Integer.parseInt(id)))
				.uniqueResult();
	}

	@Override
	public Nobreak getNobByKey(String id) throws Exception {
		return (Nobreak) sessionFactory.getCurrentSession()
				.createCriteria(Nobreak.class)
				.add(Restrictions.eq("id_nobreak", Integer.parseInt(id)))
				.uniqueResult();
	}

	@Override
	public Scanner getEscaByKey(String id) throws Exception {
		return (Scanner) sessionFactory.getCurrentSession()
				.createCriteria(Scanner.class)
				.add(Restrictions.eq("id_scanner", Integer.parseInt(id)))
				.uniqueResult();
	}

	@Override
	public Teclado getTecByKey(String id) throws Exception {
		return (Teclado) sessionFactory.getCurrentSession()
				.createCriteria(Teclado.class)
				.add(Restrictions.eq("id_teclado", Integer.parseInt(id)))
				.uniqueResult();
	}

	@Override
	public Impresora getImpByKey(String id) throws Exception {
		return (Impresora) sessionFactory.getCurrentSession()
				.createCriteria(Impresora.class)
				.add(Restrictions.eq("id_impresora", Integer.parseInt(id)))
				.uniqueResult();
	}

	@Override
	public Sistemaoperativo getSoByKey(String id) throws Exception {
		return (Sistemaoperativo) sessionFactory.getCurrentSession()
				.createCriteria(Sistemaoperativo.class)
				.add(Restrictions.eq("id_so", Integer.parseInt(id)))
				.uniqueResult();
	}

	@Override
	public Fabricante getFabByKey(String id) throws Exception {
		return (Fabricante) sessionFactory.getCurrentSession()
				.createCriteria(Fabricante.class)
				.add(Restrictions.eq("id_fabricante", Integer.parseInt(id)))
				.uniqueResult();
	}

	@Override
	public Paqueteria getPaqByKey(String id) throws Exception {
		return (Paqueteria) sessionFactory.getCurrentSession()
				.createCriteria(Paqueteria.class)
				.add(Restrictions.eq("id_paqueteria", Integer.parseInt(id)))
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

	@Override
	public Personal getPrsByKey(String id) throws Exception {
		return (Personal) sessionFactory.getCurrentSession()
				.createCriteria(Personal.class)
				.add(Restrictions.eq("id_personal", Integer.parseInt(id)))
				.uniqueResult();
	}

	@Override
	public void altaEquipo(Equipo equipo) {
		sessionFactory.getCurrentSession().save(equipo);
	}

	@Override
	public void modificarEquipo(Equipo eq1) {
		sessionFactory.getCurrentSession().merge(eq1);
	}

	@Override
	public Equipo altaEquipo(Equipo equipo, Movimientos mov) throws Exception {
		Equipo eq = (Equipo) sessionFactory.getCurrentSession().merge(equipo);
		mov.setEquipo(eq);
		eq.addMovimiento(mov);
		return (Equipo) sessionFactory.getCurrentSession().merge(eq);
	}

	@Override
	public Equipo modificarEquipoRow(Equipo eq1, Movimientos mov)
			throws Exception {
		Equipo eq = (Equipo) sessionFactory.getCurrentSession().merge(eq1);
		mov.setEquipo(eq);
		eq.addMovimiento(mov);
		return (Equipo) sessionFactory.getCurrentSession().merge(eq);
	}

	@Override
	public void borrarEquipo(Equipo eq) {
		sessionFactory.getCurrentSession().delete(eq);
	}

}