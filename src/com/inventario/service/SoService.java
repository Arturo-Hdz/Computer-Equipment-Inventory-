package com.inventario.service;

import java.util.List;
import com.inventario.model.Sistemaoperativo;


public interface SoService {

	public void altaSo(Sistemaoperativo so) throws Exception;

	public void modificarSo(Sistemaoperativo so1) throws Exception;

	public void borrarSo(Sistemaoperativo so) throws Exception;

	public List<Sistemaoperativo> getSistemaoperativo() throws Exception;

}
