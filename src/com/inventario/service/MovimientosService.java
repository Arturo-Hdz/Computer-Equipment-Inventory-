package com.inventario.service;

import java.util.List;

import com.inventario.model.Equipo;
import com.inventario.model.Area;
import com.inventario.model.Departamento;
import com.inventario.model.Movimientos;

public interface MovimientosService {

	/////////Area
	
	public void altaArea(Area area) throws Exception;

	public void modificarArea(Area area1) throws Exception;

	public void borrarArea(Area area) throws Exception;

	public List<Area> getArea() throws Exception;

	//////////Departamento
	
	public void altaDepa(Departamento depa) throws Exception;

	public void modificarDepa(Departamento depa1) throws Exception;

	public void borrarDepa(Departamento depa) throws Exception;

	public List<Departamento> getDepartamento() throws Exception;
	
	/////////Movimientos
	
	public void altaMov(Movimientos mov) throws Exception;
	
	public void modificarMovimiento(Movimientos mov1) throws Exception;

	public List<Movimientos> getMovimiento() throws Exception;
	
	public	Equipo getEqByKey(String id) throws Exception;
	
	public	Area getAreaByKey(String id) throws Exception;

	public	Departamento getDepaByKey(String id) throws Exception;
	
	public List<Equipo> getEquipos() throws Exception;
	
	public List<Departamento> getDepartamentos() throws Exception;
	
	public List<Area> getAreas() throws Exception;
	
}
