package com.inventario.service;

import java.util.List;

import com.inventario.model.Equipo;
import com.inventario.model.Paqueteria;
import com.inventario.model.Tipomantenimiento;

public interface PaqueteriaService {
	
	public void altaPaqueteria(Paqueteria pq) throws Exception;

	public void modificarPaqueteria(Paqueteria pq1) throws Exception;

	public void borrarPaqueteria(Paqueteria pq) throws Exception;

	public List<Paqueteria> getPaqueteria() throws Exception;

	///////Tipo mantenimiento
	public void altaTipoMantenimiento(Tipomantenimiento tm) throws Exception;

	public void modificarTipoMantenimiento(Tipomantenimiento tm1) throws Exception;

	public void borrarTipoMantenimiento(Tipomantenimiento tm) throws Exception;

	public List<Tipomantenimiento> getTipoMantenimiento() throws Exception;
	
	public List<Tipomantenimiento> getTipoMantenimientob() throws Exception;
	
	
	public List<Equipo> getEquipos() throws Exception;
	
	public Equipo getEqByKey(String id) throws Exception;

}	