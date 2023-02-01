package com.inventario.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.dao.PaqueteriaDao;
import com.inventario.model.Equipo;
import com.inventario.model.Paqueteria;
import com.inventario.model.Tipomantenimiento;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class PaqueteriaServiceImpl implements Serializable, PaqueteriaService {

	private static final long serialVersionUID = -7913265543851148062L;

	@Autowired
	PaqueteriaDao paqueteriaDao;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void altaPaqueteria(Paqueteria pq) throws Exception {
		paqueteriaDao.altaPaqueteria(pq);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void modificarPaqueteria(Paqueteria pq1) throws Exception {
		paqueteriaDao.modificarPaqueteria(pq1);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void borrarPaqueteria(Paqueteria pq) throws Exception {
		paqueteriaDao.borrarPaqueteria(pq);
	}

	@Override
	public List<Paqueteria> getPaqueteria() throws Exception {
		return paqueteriaDao.getPaqueteria();
	}
	
	///////Tipo mantenimiento

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void altaTipoMantenimiento(Tipomantenimiento tm) throws Exception {
		paqueteriaDao.altaTipoMantenimiento(tm);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void modificarTipoMantenimiento(Tipomantenimiento tm1) throws Exception {
		paqueteriaDao.modificarTipoMantenimiento(tm1);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void borrarTipoMantenimiento(Tipomantenimiento tm) throws Exception {
		paqueteriaDao.borrarTipoMantenimiento(tm);
	}

	@Override
	public List<Tipomantenimiento> getTipoMantenimiento() throws Exception {
		return paqueteriaDao.getTipoMantenimiento();
	}
	
	@Override
	public List<Tipomantenimiento> getTipoMantenimientob() throws Exception {
		return paqueteriaDao.getTipoMantenimientob();
	}
	
	@Override
	public List<Equipo> getEquipos() throws Exception {
		return paqueteriaDao.getEquipos();
	}
	
	@Override
	public Equipo getEqByKey(String id) throws Exception {
		return paqueteriaDao.getEqByKey(id);
	}
	
}