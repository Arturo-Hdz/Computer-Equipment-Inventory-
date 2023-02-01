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

import com.inventario.model.Sistemaoperativo;
import com.inventario.service.SoService;


@Component
@ManagedBean
@SessionScoped
public class SoBean implements Serializable {

	private static final long serialVersionUID = 3986486329710808513L;

	@Autowired
	SoService soService;

		private Sistemaoperativo so;
		private String sistemaoperativo;
		private List<Sistemaoperativo> listaSistemaoperativos;
		private Sistemaoperativo selectedSo;

		public Sistemaoperativo getSo() {
			return so;
		}

		public void setSo(Sistemaoperativo so) {
			this.so = so;
		}

		public String getSistemaoperativo() {
			return sistemaoperativo;
		}

		public void setSistemaoperativo(String sistemaoperativo) {
			this.sistemaoperativo = sistemaoperativo;
		}

		public List<Sistemaoperativo> getListaSistemaoperativos() {
			return listaSistemaoperativos;
		}

		public void setlistaSistemaoperativos(List<Sistemaoperativo> listaSistemaoperativos) {
			this.listaSistemaoperativos = listaSistemaoperativos;
		}
			
		public void resetso() {
			setSistemaoperativo("");
		}


		public void altaSo() throws Exception {
			try {
				Sistemaoperativo so = new Sistemaoperativo();
				so.setSistema_operativo(sistemaoperativo);
				soService.altaSo(so);
				llenaSo();
				resetso();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void onCellEditSo(CellEditEvent event) {
			Object oldValue = event.getOldValue();
			Object newValue = event.getNewValue();
			if (newValue != null && !newValue.equals(oldValue)) {
				DataTable p = (DataTable) event.getSource();
				Sistemaoperativo so = (Sistemaoperativo) p.getRowData();
				try {
					Sistemaoperativo so1 = new Sistemaoperativo();
					so1.setId_so(so.getId_so());
					so1.setSistema_operativo(so.getSistema_operativo());
					soService.modificarSo(so1);
					llenaSo();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		public void borrarSo() {
			Sistemaoperativo so = selectedSo;
			try {
				Sistemaoperativo so1 = new Sistemaoperativo();
				so1.setId_so(so.getId_so());
				soService.borrarSo(so);
				llenaSo();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void llenaSo() throws Exception {
			try {
				listaSistemaoperativos = soService.getSistemaoperativo();
				FacesContext context = FacesContext.getCurrentInstance();
				NavigationHandler navigationHandler = context.getApplication()
						.getNavigationHandler();
				navigationHandler.handleNavigation(context, null,
						"so?faces-redirect=true");
			} catch (Exception e) {
				e.printStackTrace();
			}
			setlistaSistemaoperativos(listaSistemaoperativos);

		}

		public Sistemaoperativo getSelectedSo() {
			return selectedSo;
		}

		public void setSelectedSo(Sistemaoperativo selectedSo) {
			this.selectedSo = selectedSo;
		}

}
