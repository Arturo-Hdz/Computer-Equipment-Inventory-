package com.inventario.dao;

import java.util.Date;
import java.util.List;

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

public interface EquipoDao {

	public List<Equipo> getFechaequipo(Date fechaIni, Date fechaFin) throws Exception;
	
	public List<Monitor> getMonitors() throws Exception;

	public List<Mouse> getMouses() throws Exception;

	public List<Nobreak> getNobreaks() throws Exception;

	public List<Scanner> getEscaners() throws Exception;

	public List<Teclado> getTeclados() throws Exception;

	public List<Impresora> getImpresoras() throws Exception;

	public List<Sistemaoperativo> getSos() throws Exception;

	public List<Fabricante> getFabs() throws Exception;

	public List<Paqueteria> getPaqs() throws Exception;

	public List<Area> getArea() throws Exception;

	public List<Departamento> getDepas() throws Exception;

	public List<Personal> getPrs() throws Exception;

	public List<Equipo> getEquipo() throws Exception;

	public List<Equipo> getEquipob() throws Exception;

	
	public Equipo getEquipoByKey(String id) throws Exception;
	
	public Monitor getMonByKey(String id) throws Exception;

	public Mouse getMouByKey(String id) throws Exception;

	public Nobreak getNobByKey(String id) throws Exception;

	public Scanner getEscaByKey(String id) throws Exception;

	public Teclado getTecByKey(String id) throws Exception;

	public Impresora getImpByKey(String id) throws Exception;

	public Sistemaoperativo getSoByKey(String id) throws Exception;

	public Fabricante getFabByKey(String id) throws Exception;

	public Paqueteria getPaqByKey(String id) throws Exception;

	public Area getAreaByKey(String id) throws Exception;

	public Departamento getDepaByKey(String id) throws Exception;

	public Personal getPrsByKey(String id) throws Exception;

		
	public Equipo altaEquipo(Equipo equipo, Movimientos mov) throws Exception;

	public void altaEquipo(Equipo equipo);
	
	public void modificarEquipo(Equipo eq1);
	
	public Equipo modificarEquipoRow(Equipo eq1, Movimientos mov) throws Exception;
	
	public void borrarEquipo(Equipo eq);

}
