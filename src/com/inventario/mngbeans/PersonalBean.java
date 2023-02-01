package com.inventario.mngbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

import com.inventario.model.Personal;
import com.inventario.service.PersonalService;

@Component
@ManagedBean
@SessionScoped
public class PersonalBean implements Serializable {

	private static final long serialVersionUID = 481375625725734384L;

	@Autowired
	PersonalService personalService;

	// //////Personal

	private Personal personal;
	private String nombre;
	private String ap_paterno;
	private String ap_materno;
	private Integer estatus;
	private List<Personal> listaPersonal;
	
	private Personal selectedPersonal;

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAp_paterno() {
		return ap_paterno;
	}

	public void setAp_paterno(String ap_paterno) {
		this.ap_paterno = ap_paterno;
	}

	public String getAp_materno() {
		return ap_materno;
	}

	public void setAp_materno(String ap_materno) {
		this.ap_materno = ap_materno;
	}
	
	public Integer getEstatus() {
		return estatus;
	}

	public void setestatus(Integer estatus) {
		this.estatus = estatus;
	}

	public List<Personal> getListaPersonal() {
		return listaPersonal;
	}

	public void setListaPersonal(List<Personal> listaPersonal) {
		this.listaPersonal = listaPersonal;
	}

	public Personal getSelectedPersonal() {
		return selectedPersonal;
	}

	public void setSelectedPersonal(Personal selectedPersonal) {
		this.selectedPersonal = selectedPersonal;
	}

	public void resetPersonal() {
		setNombre("");
		setAp_paterno("");
		setAp_materno("");
	}

	public void altaPersonal() throws Exception {
		try {
			Personal person = new Personal();
			person.setNombre(nombre);
			person.setAp_paterno(ap_paterno);
			person.setAp_materno(ap_materno);
			person.setEstatus(1);
			personalService.altaPersonal(person);
			llenaPersonal();
			resetPersonal();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void llenaPersonal() throws Exception {
		try {
				listaPersonal = personalService.getPersonal();

				FacesContext context = FacesContext.getCurrentInstance();
				NavigationHandler navigationHandler = context.getApplication()
						.getNavigationHandler();
				navigationHandler.handleNavigation(context, null,
						"personal?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setListaPersonal(listaPersonal);
	}
	
	public void onCellEditPersonal(CellEditEvent event) {
		
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		if (newValue != null && !newValue.equals(oldValue)) {
			DataTable p = (DataTable) event.getSource();
			Personal person = (Personal) p.getRowData();
			try {
				Personal person1 = new Personal();
				person1.setId_personal(person.getId_personal());
				person1.setNombre(person.getNombre());
				person1.setAp_paterno(person.getAp_paterno());
				person1.setAp_materno(person.getAp_materno());
				
				person1.setEstatus(person.getEstatus());
				if (estatus != null) {
					person1.setEstatus(estatus);
				} 
				
				personalService.modificarPersonal(person1);
				llenaPersonal();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void borrarPersonal() {
		Personal person = selectedPersonal;
		try {
			Personal person1 = new Personal();
			person1.setId_personal(person.getId_personal());
			personalService.borrarPersonal(person);
			llenaPersonal();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
