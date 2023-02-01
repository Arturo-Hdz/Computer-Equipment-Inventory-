package com.inventario.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.dao.UsuarioDao;
import com.inventario.model.Fabricante;
//import com.pizza.model.Sucursal;
import com.inventario.model.Usuario;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class UsuarioServiceImpl implements UsuarioService, Serializable {

    @Autowired
    UsuarioDao usuarioDao;
    private static final long serialVersionUID = 5003938293270429679L;

    @Override
    public Usuario getUsuarioById(Integer id) throws Exception {
	return usuarioDao.getUsuarioById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void altaUsuario(Usuario usuario) throws Exception {
	usuarioDao.altaUsuario(usuario);
    }
    
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void modificarUsuario(Usuario usuario) throws Exception {
	usuarioDao.modificarUsuario(usuario);
    }
    
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void borrarUsuario(Usuario user) throws Exception {
	usuarioDao.borrarUsuario(user);
    }
    
    @Override
    public List<Usuario> getUsuarios() throws Exception {
	return usuarioDao.getUsuarios();
    }

    @Override
    public Usuario findUsuarioByKey(String clave) throws Exception {
	return usuarioDao.findUsuarioByKey(clave);
    }

   /** @Override
    public List<Usuario> getUsuariosBySucursal(Sucursal suc) throws Exception {
	return usuarioDao.getUsuariosBySucursal(suc);
    }
	*/

	@Override
	public Usuario findUsuarioByName(String username) throws Exception {
	return usuarioDao.finUsuarioByName(username);		
	}
	
	////////fabricante
	
	 @Override
	    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	    public void altaFabricante(Fabricante fabricante) throws Exception {
		usuarioDao.altaFabricante(fabricante);
	    }
	    
	    @Override
	    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	    public void modificarFabricante(Fabricante fabricante) throws Exception {
		usuarioDao.modificarFabricante(fabricante);
	    }
	    
	    @Override
	    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	    public void borrarFabricante(Fabricante fab) throws Exception {
		usuarioDao.borrarFabricante(fab);
	    }
	    
	    @Override
	    public List<Fabricante> getFabricantes() throws Exception {
		return usuarioDao.getFabricantes();
	    }

}
