package com.inventario.dao;

import java.util.List;

import com.inventario.model.Personal;

public interface PersonalDao {
	
	/////personal
	
	public void altaPersonal(Personal person);

	public void modificarPersonal(Personal person1);

	public void borrarPersonal(Personal person);
	
	public List<Personal> getPersonal();
	
}
