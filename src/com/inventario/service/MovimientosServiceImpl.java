package com.inventario.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.dao.MovimientosDao;
import com.inventario.model.Paqueteria;
import com.inventario.model.Equipo;
import com.inventario.model.Area;
import com.inventario.model.Departamento;
import com.inventario.model.Personal;
import com.inventario.model.Movimientos;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class MovimientosServiceImpl implements Serializable, MovimientosService {

	private static final long serialVersionUID = -5052205929287736737L;

	@Autowired
	MovimientosDao movimientosDao;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void altaArea(Area area) throws Exception {
		movimientosDao.altaArea(area);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void modificarArea(Area area1) throws Exception {
		movimientosDao.modificarArea(area1);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void borrarArea(Area area) throws Exception {
		movimientosDao.borrarArea(area);
	}

	@Override
	public List<Area> getArea() throws Exception {
		return movimientosDao.getArea();
	}

	//////////Departamento
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void altaDepa(Departamento depa) throws Exception {
		movimientosDao.altaDepa(depa);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void modificarDepa(Departamento depa1) throws Exception {
		movimientosDao.modificarDepa(depa1);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void borrarDepa(Departamento depa) throws Exception {
		movimientosDao.borrarDepa(depa);
	}

	@Override
	public List<Departamento> getDepartamento() throws Exception {
		return movimientosDao.getDepartamento();
	}
	
	////////Movimientos
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void altaMov(Movimientos mov) throws Exception {
		movimientosDao.altaMov(mov);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void modificarMovimiento(Movimientos mov1) throws Exception {
		movimientosDao.modificarMovimiento(mov1);
	}
	
	@Override
	public List<Movimientos> getMovimiento() throws Exception {
		return movimientosDao.getMovimiento();
	}
	
	@Override
	public Equipo getEqByKey(String id) throws Exception {
		return movimientosDao.getEqByKey(id);
	}

	@Override
	public Area getAreaByKey(String id) throws Exception {
		return movimientosDao.getAreaByKey(id);
	}

	@Override
	public Departamento getDepaByKey(String id) throws Exception {
		return movimientosDao.getDepaByKey(id);
	}
	
	@Transactional(readOnly = false)
	@Override
	public List<Equipo> getEquipos() throws Exception {
		return movimientosDao.getEquipos();
	}
	
	@Transactional(readOnly = false)
	@Override
	public List<Departamento> getDepartamentos() throws Exception {
		return movimientosDao.getDepartamentos();
	}
	
	@Transactional(readOnly = false)
	@Override
	public List<Area> getAreas() throws Exception {
		return movimientosDao.getAreas();
	}
}