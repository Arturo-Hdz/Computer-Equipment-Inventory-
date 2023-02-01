package com.inventario.dao;

import java.util.List;

import com.inventario.model.Equipo;
import com.inventario.model.Area;
import com.inventario.model.Departamento;
import com.inventario.model.Movimientos;

public interface MovimientosDao {
	
	public void altaArea(Area area);

	public void modificarArea(Area area1);

	public void borrarArea(Area area);
	
	public List<Area> getArea();

	////////Departamento
	
	public void altaDepa(Departamento depa);

	public void modificarDepa(Departamento depa1);

	public void borrarDepa(Departamento depa);
	
	public List<Departamento> getDepartamento();
	
	///////Movimientos
	
	public void altaMov(Movimientos mov);
	
	public void modificarMovimiento(Movimientos mov1);
	
	public List<Movimientos> getMovimiento();
	
	public Equipo getEqByKey(String id) throws Exception;
	
	public Area getAreaByKey(String id) throws Exception;
	
	public Departamento getDepaByKey(String id) throws Exception;
	
	public List<Equipo> getEquipos() throws Exception;
	
	public List<Departamento> getDepartamentos() throws Exception;
	
	public List<Area> getAreas() throws Exception;

}
