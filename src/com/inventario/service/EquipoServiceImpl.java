package com.inventario.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
import com.inventario.dao.EquipoDao;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class EquipoServiceImpl implements Serializable, EquipoService {

	private static final long serialVersionUID = -8238972084020109020L;

	@Autowired
	EquipoDao equipoDao;
	
	  @Override
	    public List<Equipo> getFechaequipo(Date fechaIni, Date fechaFin) throws Exception {
		return equipoDao.getFechaequipo(fechaIni, fechaFin);
	    }

	@Transactional(readOnly = false)
	@Override
	public List<Monitor> getMonitors() throws Exception {
		return equipoDao.getMonitors();
	}
	
	@Transactional(readOnly = false)
	@Override
	public List<Mouse> getMouses() throws Exception {
		return equipoDao.getMouses();
	}
	
	@Transactional(readOnly = false)
	@Override
	public List<Nobreak> getNobreaks() throws Exception {
		return equipoDao.getNobreaks();
	}
	
	@Transactional(readOnly = false)
	@Override
	public List<Scanner> getEscaners() throws Exception {
		return equipoDao.getEscaners();
	}
	
	@Transactional(readOnly = false)
	@Override
	public List<Teclado> getTeclados() throws Exception {
		return equipoDao.getTeclados();
	}
	
	@Transactional(readOnly = false)
	@Override
	public List<Impresora> getImpresoras() throws Exception {
		return equipoDao.getImpresoras();
	}
	
	@Transactional(readOnly = false)
	@Override
	public List<Sistemaoperativo> getSos() throws Exception {
		return equipoDao.getSos();
	}
	@Transactional(readOnly = false)
	@Override
	public List<Fabricante> getFabs() throws Exception {
		return equipoDao.getFabs();
	}
	@Transactional(readOnly = false)
	@Override
	public List<Paqueteria> getPaqs() throws Exception {
		return equipoDao.getPaqs();
	}
	@Transactional(readOnly = false)
	@Override
	public List<Area> getArea() throws Exception {
		return equipoDao.getArea();
	}
	@Transactional(readOnly = false)
	@Override
	public List<Departamento> getDepas() throws Exception {
		return equipoDao.getDepas();
	}
	@Transactional(readOnly = false)
	@Override
	public List<Personal> getPrs() throws Exception {
		return equipoDao.getPrs();
	}
	
	
	@Override
	public List<Equipo> getEquipo() throws Exception {
		return equipoDao.getEquipo();
	}
	
	@Override
	public List<Equipo> getEquipob() throws Exception {
		return equipoDao.getEquipob();
	}
	
	
	@Override
	public Equipo getEquipoByKey(String id) throws Exception {
		return equipoDao.getEquipoByKey(id);
	}
	
	@Override
	public Monitor getMonByKey(String id) throws Exception {
		return equipoDao.getMonByKey(id);
	}
	
	@Override
	public Mouse getMouByKey(String id) throws Exception {
		return equipoDao.getMouByKey(id);
	}
	
	@Override
	public Nobreak getNobByKey(String id) throws Exception {
		return equipoDao.getNobByKey(id);
	}
	
	@Override
	public Scanner getEscaByKey(String id) throws Exception {
		return equipoDao.getEscaByKey(id);
	}
	
	@Override
	public Teclado getTecByKey(String id) throws Exception {
		return equipoDao.getTecByKey(id);
	}
	
	@Override
	public Impresora getImpByKey(String id) throws Exception {
		return equipoDao.getImpByKey(id);
	}
	
	@Override
	public Sistemaoperativo getSoByKey(String id) throws Exception {
		return equipoDao.getSoByKey(id);
	}
	@Override
	public Fabricante getFabByKey(String id) throws Exception {
		return equipoDao.getFabByKey(id);
	}@Override
	public Paqueteria getPaqByKey(String id) throws Exception {
		return equipoDao.getPaqByKey(id);
	}
	@Override
	public Area getAreaByKey(String id) throws Exception {
		return equipoDao.getAreaByKey(id);
	}@Override
	public Departamento getDepaByKey(String id) throws Exception {
		return equipoDao.getDepaByKey(id);
	}
	@Override
	public Personal getPrsByKey(String id) throws Exception {
		return equipoDao.getPrsByKey(id);
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void altaEquipo(Equipo equipo) throws Exception {
		equipoDao.altaEquipo(equipo);
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void modificarEquipo(Equipo eq1) throws Exception {
		equipoDao.modificarEquipo(eq1);
	}
	
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public Equipo altaEquipo(Equipo equipo, Movimientos mov) throws Exception {
		return equipoDao.altaEquipo(equipo, mov);
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public Equipo modificarEquipoRow(Equipo eq1, Movimientos mov) throws Exception {
		return equipoDao.modificarEquipoRow(eq1, mov);
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void borrarEquipo(Equipo eq) throws Exception {
		equipoDao.borrarEquipo(eq);
	}

}