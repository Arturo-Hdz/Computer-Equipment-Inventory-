package com.inventario.dao;

import java.util.List;

import com.inventario.model.Monitor;
import com.inventario.model.Mouse;

public interface MouseDao {

	public void altaMouse(Mouse mouse);

	public void modificarMouse(Mouse mou1);

	public void borrarMouse(Mouse mou);
	
	public List<Mouse> getMouse();
	
	/////Monitor
	
	public void altaMon(Monitor mon);

	public void modificarMon(Monitor mon1);

	public void borrarMon(Monitor mon);
	
	public List<Monitor> getMonitor();
	
}
