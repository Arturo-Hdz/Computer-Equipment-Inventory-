package com.inventario.dao;

import java.util.List;

import com.inventario.model.Fabricante;
import com.inventario.model.Impresora;
import com.inventario.model.Teclado;

public interface ImpresoraDao {

	public void altaImpre(Impresora impre);

	public void modificarImpre(Impresora impre1);

	public void borrarImpre(Impresora impre);
	
	public List<Impresora> getImpresora();
	
	/////Teclado
	
	public void altaTecla(Teclado tecla);

	public void modificarTecla(Teclado tecla1);

	public void borrarTecla(Teclado tecla);
	
	public List<Teclado> getTeclado();
	
}
