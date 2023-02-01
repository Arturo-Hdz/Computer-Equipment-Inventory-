package com.inventario.service;

import java.util.List;

import com.inventario.model.Scanner;
import com.inventario.model.Nobreak;

public interface EscanerService {
	
	public void altaEsca(Scanner esca) throws Exception;

	public void modificarEsca(Scanner esca1) throws Exception;

	public void borrarEsca(Scanner esca) throws Exception;

	public List<Scanner> getEscaner() throws Exception;
	
	/////Teclado
	
	public void altaNob(Nobreak nob) throws Exception;

	public void modificarNob(Nobreak nob1) throws Exception;

	public void borrarNob(Nobreak nob) throws Exception;

	public List<Nobreak> getNobreak() throws Exception;

}
