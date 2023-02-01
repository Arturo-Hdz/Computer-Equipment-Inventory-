package com.inventario.dao;

import java.util.List;

import com.inventario.model.Equipo;
import com.inventario.model.Paqueteria;
import com.inventario.model.Tipomantenimiento;

public interface PaqueteriaDao {

	public void altaPaqueteria(Paqueteria pq);

	public void modificarPaqueteria(Paqueteria pq1);

	public void borrarPaqueteria(Paqueteria pq);

	public List<Paqueteria> getPaqueteria();

	// ////Tipo mantenimiento
	public void altaTipoMantenimiento(Tipomantenimiento tm);

	public void modificarTipoMantenimiento(Tipomantenimiento tm1);

	public void borrarTipoMantenimiento(Tipomantenimiento tm);

	public List<Tipomantenimiento> getTipoMantenimiento();

	public List<Tipomantenimiento> getTipoMantenimientob();

	
	public List<Equipo> getEquipos();

	public Equipo getEqByKey(String id) throws Exception;
}