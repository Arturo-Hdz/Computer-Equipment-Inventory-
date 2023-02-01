package com.inventario.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.inventario.model.Fabricante;
//import com.pizza.model.Sucursal;
import com.inventario.model.Usuario;

public class UsuarioDaoImpl implements UsuarioDao, Serializable {

	private static final long serialVersionUID = 5060030512992606564L;
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Usuario getUsuarioById(Integer id) {
		return (Usuario) sessionFactory.getCurrentSession()
				.createCriteria(Usuario.class).add(Restrictions.eq("id", id))
				.add(Restrictions.eq("estatus", 1)).uniqueResult();
	}

	@Override
	public void altaUsuario(Usuario usuario) {
		sessionFactory.getCurrentSession().save(usuario);
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		sessionFactory.getCurrentSession().saveOrUpdate(usuario);
	}
	
	@Override
	public void borrarUsuario(Usuario user) {
		sessionFactory.getCurrentSession().delete(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> getUsuarios() {
		return sessionFactory.getCurrentSession()
				.createCriteria(Usuario.class)
				// .createAlias("grupo", "gpo")
				// .createAlias("sucursal", "suc")
				.add(Restrictions.eq("estatus", 1))
				.addOrder(Order.asc("id"))
				.list();
	}

	@Override
	public Usuario findUsuarioByKey(String clave) throws Exception {
		return (Usuario) sessionFactory.getCurrentSession()
				.createCriteria(Usuario.class)
				.add(Restrictions.eq("estatus", 1))
				.add(Restrictions.eq("id", Integer.parseInt(clave)))
				.uniqueResult();
	}

	/**
	 * @SuppressWarnings("unchecked")
	 * @Override public List<Usuario> getUsuariosBySucursal(Sucursal suc) throws
	 *           Exception { return
	 *           sessionFactory.getCurrentSession().createCriteria
	 *           (Usuario.class) .add(Restrictions.eq("estatus", 1))
	 *           .add(Restrictions.eq("sucursal", suc))
	 *           .addOrder(Order.asc("id")).list(); }
	 */

	@Override
	public Usuario finUsuarioByName(String username) throws Exception {
		return (Usuario) sessionFactory.getCurrentSession()
				.createCriteria(Usuario.class)
				.add(Restrictions.eq("estatus", 1))
				.add(Restrictions.eq("usuario", username)).uniqueResult();

	}

	// fabricante

	@Override
	public void altaFabricante(Fabricante fabricante) {
		sessionFactory.getCurrentSession().save(fabricante);
	}

	@Override
	public void modificarFabricante(Fabricante fabricante) {
		sessionFactory.getCurrentSession().saveOrUpdate(fabricante);
	}
	
	@Override
	public void borrarFabricante(Fabricante fab) {
		sessionFactory.getCurrentSession().delete(fab);
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Fabricante> getFabricantes() {
		return sessionFactory.getCurrentSession()
				.createCriteria(Fabricante.class).addOrder(Order.asc("id_fabricante"))
				.list();
	}

}
