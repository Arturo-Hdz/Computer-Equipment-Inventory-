package com.inventario.dao;

import java.util.List;

import com.inventario.model.Scanner;
import com.inventario.model.Nobreak;

public interface EscanerDao {

	public void altaEsca(Scanner esca);

	public void modificarEsca(Scanner esca1);

	public void borrarEsca(Scanner esca);
	
	public List<Scanner> getEscaner();
	
	/////Nobreak
	
	public void altaNob(Nobreak nob);

	public void modificarNob(Nobreak nob1);

	public void borrarNob(Nobreak nob);
	
	public List<Nobreak> getNobreak();
	
	
}
