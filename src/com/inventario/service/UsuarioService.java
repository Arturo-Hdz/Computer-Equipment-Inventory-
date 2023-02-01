package com.inventario.service;

import java.util.List;

import com.inventario.model.Fabricante;
//import com.pizza.model.Sucursal;
import com.inventario.model.Usuario;

public interface UsuarioService {
	
	public void altaUsuario(Usuario usuario) throws Exception;
	
	public void modificarUsuario(Usuario usuario) throws Exception;
	
	public void borrarUsuario(Usuario user) throws Exception;

    public Usuario getUsuarioById(Integer id) throws Exception;

    public List<Usuario> getUsuarios() throws Exception;

    public Usuario findUsuarioByKey(String clave) throws Exception;

  //  public List<Usuario> getUsuariosBySucursal(Sucursal suc) throws Exception;

	public Usuario  findUsuarioByName(String username) throws Exception;

	//////////////////fabricante
	
	public void altaFabricante(Fabricante fab) throws Exception;

	public void modificarFabricante(Fabricante fab1) throws Exception;

	public void borrarFabricante(Fabricante fab) throws Exception;
	
	public List<Fabricante> getFabricantes() throws Exception;

}
