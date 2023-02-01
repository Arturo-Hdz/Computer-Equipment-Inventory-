package com.inventario.mngbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inventario.model.Fabricante;
import com.inventario.model.Personal;
import com.inventario.model.Usuario;
import com.inventario.service.UsuarioService;

@Component
@ManagedBean
@SessionScoped
public class UserManagedBean implements Serializable {

	@Autowired
	UsuarioService usuarioService;

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private String username;
	private String password;
	private String password2;
	private String mail;
	private String message = "";
	private String contrasena;
	private Integer estatus;
	private Usuario selectedUser;

	private List<Usuario> listaUsuarios;

	public String login() {
		Usuario usr = new Usuario();
		// Verificar el usuario y el password
		try {
			usr = usuarioService.findUsuarioByName(username);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (usr != null && usr.getPassword().equals(password)) {
			setUsuario(usr);
			return "views/home";
		} 

		else {
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle prop = ResourceBundle.getBundle("messages");
			message = prop.getString("login.incorrecto");
			context.addMessage("username", new FacesMessage(message));
			return "default";
		}
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario user) {
		this.usuario = user;
	}

	public String getmail() {
		return mail;
	}

	public void setmail(String email) {
		this.mail = email;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public void altausuario() throws Exception {
		try {
			Usuario usr = new Usuario();
			usr.setUsuario(username);
			usr.setPassword(password);
			usr.setEmail(mail);
			usr.setEstatus(1);
			usuarioService.altaUsuario(usr);
			llenausuario();
			reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * public void borrarusuario() { Usuario user = new Usuario(); try { Usuario
	 * usr = new Usuario(); usr = usuarioService.findUsuarioByName2(id);
	 * usuarioService.borrarUsuario(usr); llenausuario(); } catch (Exception e)
	 * { e.printStackTrace(); } } public List<Sucursal> getListsucursal() {
	 * List<Sucursal> sucursales = new ArrayList<Sucursal>(); FacesMessage msg =
	 * new FacesMessage(); try { sucursales = sucursalService.getSucursales(); }
	 * catch (Exception e) { msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	 * msg.setSummary("Error al consultar sucursales!" + e);
	 * FacesContext.getCurrentInstance().addMessage(null, msg); } return
	 * sucursales; }
	 */
	public void llenausuario() throws Exception {

		try {
			listaUsuarios = usuarioService.getUsuarios();
			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"usuarios?faces-redirect=true");

		} catch (Exception e) {
			e.printStackTrace();
		}
		setListaUsuarios(listaUsuarios);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		if (newValue != null && !newValue.equals(oldValue)) {
			DataTable p = (DataTable) event.getSource();
			Usuario usr = (Usuario) p.getRowData();
			try {
				Usuario user = new Usuario();
				user.setId(usr.getId());
				user.setUsuario(usr.getUsuario());

				if (contrasena != null) {
					user.setPassword(contrasena);
				} else {
					user.setPassword(usr.getPassword());
				}
				user.setEmail(usr.getEmail());
				
				if(estatus != null){
					user.setEstatus(estatus);
				}
				
				usuarioService.modificarUsuario(user);
				/*
				 * if (usuario.getGrupo().getId() == 2) {
				 * usuarioService.modificarUsuario(user); }
				 */
				contrasena = null;
				llenausuario();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setListaUsuarios(List<Usuario> listausuarios) {
		this.listaUsuarios = listausuarios;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void reset() {
		setUsername("");
		setPassword("");
		setmail("");
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Usuario getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(Usuario selectedUser) {
		this.selectedUser = selectedUser;
	}
	
	public void borrarUsuario() {
		Usuario user = selectedUser;
		try {
			Usuario user1 = new Usuario();
			user1.setId(usuario.getId());
			usuarioService.borrarUsuario(user);
			llenausuario();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ////////////////////////Fabricante

	private Fabricante marca;
	private String fabricante;
	private List<Fabricante> listaFabricantes;
	private Fabricante selectedFab;

	public Fabricante getMarca() {
		return marca;
	}

	public void setMarca(Fabricante marca) {
		this.marca = marca;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public List<Fabricante> getListaFabricantes() {
		return listaFabricantes;
	}

	public void setListaFabricantes(List<Fabricante> listaFabricantes) {
		this.listaFabricantes = listaFabricantes;
	}

	public void resetfab() {
		setFabricante("");
	}

	public void altaFabricante() throws Exception {
		try {
			Fabricante fab = new Fabricante();
			fab.setFabricante(fabricante);
			usuarioService.altaFabricante(fab);
			llenafabricante();
			resetfab();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onCellEditFab(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		if (newValue != null && !newValue.equals(oldValue)) {

			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Celda Modificada", "Viejo: " + oldValue + ", Nuevo:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);

			DataTable p = (DataTable) event.getSource();
			Fabricante fab = (Fabricante) p.getRowData();
			try {
				Fabricante fab1 = new Fabricante();
				fab1.setId_fabricante(fab.getId_fabricante());
				fab1.setFabricante(fab.getFabricante());
				usuarioService.modificarFabricante(fab1);
				llenafabricante();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void borrarfabricante() {
		Fabricante fab = selectedFab;
		try {
			Fabricante fab1 = new Fabricante();
			fab1.setId_fabricante(fab.getId_fabricante());
			usuarioService.borrarFabricante(fab);
			llenafabricante();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void llenafabricante() throws Exception {
		try {
			listaFabricantes = usuarioService.getFabricantes();
			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"fabricante?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setListaFabricantes(listaFabricantes);

	}

	public Fabricante getSelectedFab() {
		return selectedFab;
	}

	public void setSelectedFab(Fabricante selectedFab) {
		this.selectedFab = selectedFab;
	}

}
