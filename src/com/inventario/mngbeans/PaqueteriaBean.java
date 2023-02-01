package com.inventario.mngbeans;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.servlet.ServletContext;
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

import com.inventario.model.Paqueteria;
import com.inventario.model.Tipomantenimiento;
import com.inventario.model.Equipo;
import com.inventario.service.PaqueteriaService;
import com.inventario.service.EquipoService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

@Component
@ManagedBean
@SessionScoped
public class PaqueteriaBean implements Serializable {

	private static final long serialVersionUID = -7938694501721310728L;

	@Autowired
	PaqueteriaService paqueteriaService;

	private Paqueteria pq;
	private String office;
	private String descripcion;
	private List<Paqueteria> listaPaqueteria;
	private Paqueteria selectedPaq;

	public Paqueteria getPq() {
		return pq;
	}

	public void setPq(Paqueteria pq) {
		this.pq = pq;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getdescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Paqueteria> getListaPaqueteria() {
		return listaPaqueteria;
	}

	public void setlistaPaqueteria(List<Paqueteria> listaPaqueteria) {
		this.listaPaqueteria = listaPaqueteria;
	}

	public void resetPq() {
		setOffice("");
		setDescripcion("");
	}

	public void altaPaqueteria() throws Exception {
		try {
			Paqueteria pq = new Paqueteria();
			pq.setOffice(office);
			pq.setDescripcion(descripcion);
			paqueteriaService.altaPaqueteria(pq);
			llenaPq();
			resetPq();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onCellEditPq(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		if (newValue != null && !newValue.equals(oldValue)) {
			DataTable p = (DataTable) event.getSource();
			Paqueteria pq = (Paqueteria) p.getRowData();
			try {
				Paqueteria pq1 = new Paqueteria();
				pq1.setId_paqueteria(pq.getId_paqueteria());
				pq1.setOffice(pq.getOffice());
				pq1.setDescripcion(pq.getDescripcion());
				paqueteriaService.modificarPaqueteria(pq1);
				llenaPq();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void borrarPq() {
		Paqueteria pq = selectedPaq;
		try {
			Paqueteria pq1 = new Paqueteria();
			pq1.setId_paqueteria(pq.getId_paqueteria());
			paqueteriaService.borrarPaqueteria(pq);
			llenaPq();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void llenaPq() throws Exception {
		try {
			listaPaqueteria = paqueteriaService.getPaqueteria();
			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"paqueteria?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setlistaPaqueteria(listaPaqueteria);

	}

	public Paqueteria getSelectedPaq() {
		return selectedPaq;
	}

	public void setSelectedPaq(Paqueteria selectedPqq) {
		this.selectedPaq = selectedPqq;
	}

	// //////////////////Tipo Mantenimiento

	private Tipomantenimiento tm;
	private Date fecha1;
	private Date fecha2;
	private String tipo_mantenimiento;
	private String observacion;
	private String reparacion;
	private Integer estatus;
	private List<Tipomantenimiento> listaTm;
	private Tipomantenimiento selectedTm;

	private List<Tipomantenimiento> filteredTms;

	public List<Tipomantenimiento> getFilteredTms() {
		return filteredTms;
	}

	public void setFilteredTms(List<Tipomantenimiento> filteredTms) {
		this.filteredTms = filteredTms;
	}

	@Autowired
	EquipoService equipoService;

	private String nuevoeq;
	private String actualeq;

	public String getNuevoeq() {
		return nuevoeq;
	}

	public void setNuevoeq(String nuevoeq) {
		this.nuevoeq = nuevoeq;
	}

	public String getActualeq() {
		return actualeq;
	}

	public void setActualeq(String actualeq) {
		this.actualeq = actualeq;
	}

	public Tipomantenimiento getTm() {
		return tm;
	}

	public void setTm(Tipomantenimiento tm) {
		this.tm = tm;
	}

	public Date getFecha1() {
		return fecha1;
	}

	public void setFecha1(Date fecha1) {
		this.fecha1 = fecha1;
	}

	public Date getFecha2() {
		return fecha2;
	}

	public void setFecha2(Date fecha2) {
		this.fecha2 = fecha2;
	}

	public String getTipo_mantenimiento() {
		return tipo_mantenimiento;
	}

	public void setTipo_mantenimiento(String tipo_mantenimiento) {
		this.tipo_mantenimiento = tipo_mantenimiento;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getReparacion() {
		return reparacion;
	}

	public void setReparacion(String reparacion) {
		this.reparacion = reparacion;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public List<Tipomantenimiento> getListaTm() {
		return listaTm;
	}

	public void setlistaTm(List<Tipomantenimiento> listaTm) {
		this.listaTm = listaTm;
	}

	public List<Equipo> getEquipos1() {
		List<Equipo> eq = new ArrayList<Equipo>();
		FacesMessage msg = new FacesMessage();

		try {
			eq = paqueteriaService.getEquipos();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar equipos!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return eq;
	}

	public List<Equipo> getEquipos2() {
		List<Equipo> eq = new ArrayList<Equipo>();
		FacesMessage msg = new FacesMessage();

		try {
			eq = paqueteriaService.getEquipos();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar equipos!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return eq;
	}

	public void resetTm() {
		setFecha1(null);
		setFecha2(null);
		setNuevoeq("");
		setTipo_mantenimiento(null);
		setObservacion("");
	}

	// /////calendario
	public void click() {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.update("form2:display");
		requestContext.execute("PF('dlg').show()");
	}

	public void altaTipoMantenimiento() throws Exception {
		System.out.println(nuevoeq);

		try {
			Tipomantenimiento tm = new Tipomantenimiento();
			tm.setFecha(fecha1);
			tm.setFecha2(fecha2);
			tm.setEquipo(paqueteriaService.getEqByKey(nuevoeq));
			tm.setTipo_mantenimiento(tipo_mantenimiento);
			tm.setObservacion(observacion);
			tm.setReparacion("En Proceso");
			tm.setEstatus(1);
			paqueteriaService.altaTipoMantenimiento(tm);
			llenaTm();
			resetTm();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onCellEditTm(CellEditEvent event) {
		System.out.println(actualeq);

		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		if (newValue != null && !newValue.equals(oldValue)) {
			DataTable p = (DataTable) event.getSource();
			Tipomantenimiento tm = (Tipomantenimiento) p.getRowData();
			try {
				Tipomantenimiento tm1 = new Tipomantenimiento();
				tm1.setId_tipo_mantenimiento(tm.getId_tipo_mantenimiento());
				tm1.setFecha(tm.getFecha());
				tm1.setFecha2(tm.getFecha2());
				tm1.setTipo_mantenimiento(tipo_mantenimiento);
				tm1.setObservacion(tm.getObservacion());

				tm1.setReparacion(tm.getReparacion());
				if (reparacion != null) {
					tm1.setReparacion(reparacion);
				}

				tm1.setEstatus(tm.getEstatus());
				if (estatus != null) {
					tm1.setEstatus(estatus);
				}

				// combo
				tm1.setTipo_mantenimiento(tm.getTipo_mantenimiento());
				if (tipo_mantenimiento != null) {
					tm1.setTipo_mantenimiento(tipo_mantenimiento);
				}

				tm1.setEquipo(tm.getEquipo());
				if (actualeq != null) {
					tm1.setEquipo(paqueteriaService.getEqByKey(actualeq));
				}

				paqueteriaService.modificarTipoMantenimiento(tm1);

				tipo_mantenimiento = (null);
				actualeq = ("");
				reparacion = (null);
				estatus = (null);
				llenaTm();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void borrarTm() {
		Tipomantenimiento tm = selectedTm;
		try {
			Tipomantenimiento tm1 = new Tipomantenimiento();
			tm1.setId_tipo_mantenimiento(tm.getId_tipo_mantenimiento());
			paqueteriaService.borrarTipoMantenimiento(tm);
			llenaTm();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void llenaTm() throws Exception {
		try {
			listaTm = paqueteriaService.getTipoMantenimiento();
			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"tipomantenimiento?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setlistaTm(listaTm);

	}

	public void llenaTm1() throws Exception {
		try {
			listaTm = paqueteriaService.getTipoMantenimiento();

			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"reportetm1?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setlistaTm(listaTm);
	}

	public void llenaTm2() throws Exception {
		try {
			listaTm = paqueteriaService.getTipoMantenimiento();
			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"reportetm2?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setlistaTm(listaTm);

	}
	
	public void llenaTm3() throws Exception {
		try {
			listaTm = paqueteriaService.getTipoMantenimiento();

			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"reportetm3?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setlistaTm(listaTm);
	}

	public void llenaTm4() throws Exception {
		try {
			listaTm = paqueteriaService.getTipoMantenimientob();

			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"reportetm4?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setlistaTm(listaTm);
	}

	public void llenaTm5() throws Exception {
		try {
			listaTm = paqueteriaService.getTipoMantenimientob();

			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"reportetm5?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setlistaTm(listaTm);
	}

	public void llenaTm6() throws Exception {
		try {
			listaTm = paqueteriaService.getTipoMantenimientob();

			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"reportetm6?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setlistaTm(listaTm);
	}

	public Tipomantenimiento getSelectedTm() {
		return selectedTm;
	}

	public void setSelectedTm(Tipomantenimiento selectedTm) {
		this.selectedTm = selectedTm;
	}

	private String fileName;

	public String getFileName() {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmss");
		fileName = "Reporte-Equipo-Mantenimiento".concat(formatter
				.format(new Date()));

		return fileName;
	}

	public void postProcessXLS(Object document) {
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow header = sheet.getRow(0);

		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
			HSSFCell cell = header.getCell(i);

			cell.setCellStyle(cellStyle);
		}
	}

	public void preProcessPDF(Object document) throws IOException,
			DocumentException {

		final Document pdf = (Document) document;

		pdf.setPageSize(PageSize.A4.rotate());
		pdf.open();

		PdfPTable pdfTable = new PdfPTable(2);

		pdfTable.setWidthPercentage(40f);
		pdfTable.setHorizontalAlignment(0);
		pdf.add(pdfTable);

		ServletContext servletContext = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();
		String logo = servletContext.getRealPath("") + File.separator
				+ "images" + File.separator + "cmt.jpg";

		final Phrase phrase = new Phrase("Reporte Matenimiento Equipos");
		pdf.add(Image.getInstance(logo));
		pdf.add(phrase);
	}

	public void postProcessPDF(Object document) throws IOException,
			DocumentException {
		final Document pdf = (Document) document;
		pdf.setPageSize(PageSize.A4.rotate());

	}

}
