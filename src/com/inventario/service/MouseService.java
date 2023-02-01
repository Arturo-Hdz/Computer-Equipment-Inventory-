package com.inventario.service;

import java.util.List;

import com.inventario.model.Monitor;
import com.inventario.model.Mouse;

public interface MouseService {
	
	public void altaMouse(Mouse mou) throws Exception;

	public void modificarMouse(Mouse mou1) throws Exception;

	public void borrarMouse(Mouse mou) throws Exception;

	public List<Mouse> getMouse() throws Exception;
	
	/////Monitor
	
	public void altaMon(Monitor mon) throws Exception;

	public void modificarMon(Monitor mon1) throws Exception;

	public void borrarMon(Monitor mon) throws Exception;

	public List<Monitor> getMonitor() throws Exception;

}
