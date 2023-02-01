package com.inventario.service;

import java.util.List;

import com.inventario.model.Personal;

public interface PersonalService {
	
	//////Personal
	
	public void altaPersonal(Personal person) throws Exception;

	public void modificarPersonal(Personal person1) throws Exception;

	public void borrarPersonal(Personal person) throws Exception;

	public List<Personal> getPersonal() throws Exception;
	
}
