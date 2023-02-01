package com.inventario.service;

import java.util.List;

import com.inventario.model.Impresora;
import com.inventario.model.Teclado;

public interface ImpresoraService {
	
	public void altaImpre(Impresora impre) throws Exception;

	public void modificarImpre(Impresora impre1) throws Exception;

	public void borrarImpre(Impresora impre) throws Exception;

	public List<Impresora> getImpresora() throws Exception;
	
	/////Teclado
	
	public void altaTecla(Teclado tecla) throws Exception;

	public void modificarTecla(Teclado tecla1) throws Exception;

	public void borrarTecla(Teclado tecla) throws Exception;

	public List<Teclado> getTeclado() throws Exception;
	
}
