package com.inventario.dao;


import java.util.List;

import com.inventario.model.Sistemaoperativo;

public interface SoDao {
	

	public void altaSo(Sistemaoperativo so);

	public void modificarSo(Sistemaoperativo so);

	public void borrarSo(Sistemaoperativo so);
	
	public List<Sistemaoperativo> getSistemaoperativo();

}
