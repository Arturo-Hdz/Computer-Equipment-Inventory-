package com.inventario.mngbeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import java.io.File;
import java.io.IOException;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inventario.model.Equipo;
import com.inventario.model.Movimientos;
import com.inventario.model.Monitor;
import com.inventario.model.Mouse;
import com.inventario.model.Nobreak;
import com.inventario.model.Scanner;
import com.inventario.model.Teclado;
import com.inventario.model.Impresora;
import com.inventario.model.Sistemaoperativo;
import com.inventario.model.Fabricante;
import com.inventario.model.Paqueteria;
import com.inventario.model.Area;
import com.inventario.model.Departamento;
import com.inventario.model.Personal;
import com.inventario.service.EquipoService;
import com.inventario.service.MouseService;
import com.inventario.service.EscanerService;
import com.inventario.service.ImpresoraService;
import com.inventario.service.SoService;
import com.inventario.service.PaqueteriaService;
import com.inventario.service.MovimientosService;
import com.inventario.service.PersonalService;
import com.inventario.service.UsuarioService;
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
public class EquipoBean implements Serializable {

	private static final long serialVersionUID = 1312587746301582834L;

	@Autowired
	EquipoService equipoService;

	private Equipo equipo;
	private List<Equipo> listaequipo;
	private int id_equipo;
	private Date fecha;
	private String no_serie;
	private String modelo;
	private String nombre_equipo;
	private String antivirus;
	private Integer ip;
	private String observaciones;
	private Integer estatus;
	
	//reporte y seleccion de equipo
	private Equipo selectedEquipo;
	private Date fechaIni;
	private Date fechaFin;
	private List <Equipo>filtroEquipos;
	private List<Equipo>Fechaequipo;

	public Equipo getSelectedEquipo() {
		return selectedEquipo;
	}

	public void setSelectedEquipo(Equipo selectedEquipo) {
		this.selectedEquipo = selectedEquipo;
	}
	
	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public List <Equipo> getFiltroEquipos() {
		return filtroEquipos;
	}

	public void setFiltroEquipos(List <Equipo> filtroEquipos) {
		this.filtroEquipos = filtroEquipos;
	}

	public List<Equipo> getFechaequipo() {
		return Fechaequipo;
	}

	public void setFechaequipo(List<Equipo> fechaequipo) {
		Fechaequipo = fechaequipo;
	}

	public void buscarEquipos() throws Exception {
		List<Equipo> equi = new ArrayList<Equipo>();
		equi = equipoService.getFechaequipo(getFechaIni(), getFechaFin());
		setFechaequipo(equi);
	}
	
	public void limpiarEquipos() throws Exception {
		List<Equipo> equi = new ArrayList<Equipo>();
		setFechaequipo(equi);
	}

	private List<Equipo> filteredEquipos;
	
	public List<Equipo> getFilteredEquipos() {
		return filteredEquipos;
	}

	public void setFilteredEquipos(List<Equipo> filteredEquipos) {
		this.filteredEquipos = filteredEquipos;
	}
	
	private List<Area> busqarea;
	
	public List<Area> getBusqarea() {
		return busqarea;
	}

	public void setBusqarea(List<Area> busqarea) {
		this.busqarea = busqarea;
	}
	
	public void busqarea() throws Exception {
		try {
			busqarea = equipoService.getArea();

		} catch (Exception e) {
			e.printStackTrace();
		}
		setBusqarea(busqarea);
	}


	
	// monitor y mouse
	@Autowired
	MouseService mouseService;
	private String actualmon;
	private String nuevomon;
	private String actualmou;
	private String nuevomou;

	// nobreak y escaner
	@Autowired
	EscanerService escanerService;
	private String actualnob;
	private String nuevonob;
	private String actualesca;
	private String nuevoesca;

	// teclado y impresora
	@Autowired
	ImpresoraService impresoraService;
	private String actualtec;
	private String nuevotec;
	private String actualimp;
	private String nuevoimp;

	@Autowired
	SoService soService;
	private String actualso;
	private String nuevoso;

	// Fabricante
	@Autowired
	UsuarioService usuarioService;
	private String actualfab;
	private String nuevofab;

	// Paqueteria y Tipo mantenimiento
	@Autowired
	PaqueteriaService paqueteriaService;
	private String actualpaq;
	private String nuevopaq;

	@Autowired
	MovimientosService movimientosService;
	private String actualarea;
	private String nuevaarea;
	private String actualdepa;
	private String nuevodepa;

	@Autowired
	PersonalService personalService;
	private String actualprs;
	private String nuevoprs;

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public List<Equipo> getListaequipo() {
		return listaequipo;
	}

	public void setListaequipo(List<Equipo> listaequipo) {
		this.listaequipo = listaequipo;
	}
	
	public int getId_equipo() {
		return id_equipo;
	}

	public void setId_equipo(int id_equipo) {
		this.id_equipo = id_equipo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNo_serie() {
		return no_serie;
	}

	public void setNo_serie(String no_serie) {
		this.no_serie = no_serie;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNombre_equipo() {
		return nombre_equipo;
	}

	public void setNombre_equipo(String nombre_equipo) {
		this.nombre_equipo = nombre_equipo;
	}

	public String getAntivirus() {
		return antivirus;
	}

	public void setAntivirus(String antivirus) {
		this.antivirus = antivirus;
	}

	public Integer getIp() {
		return ip;
	}

	public void setIp(Integer ip) {
		this.ip = ip;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public String getActualmon() {
		return actualmon;
	}

	public void setActualmon(String actualmon) {
		this.actualmon = actualmon;
	}

	public String getNuevomon() {
		return nuevomon;
	}

	public void setNuevomon(String nuevomon) {
		this.nuevomon = nuevomon;
	}

	public String getActualmou() {
		return actualmou;
	}

	public void setActualmou(String actualmou) {
		this.actualmou = actualmou;
	}

	public String getNuevomou() {
		return nuevomou;
	}

	public void setNuevomou(String nuevomou) {
		this.nuevomou = nuevomou;
	}

	public String getActualnob() {
		return actualnob;
	}

	public void setActualnob(String actualnob) {
		this.actualnob = actualnob;
	}

	public String getNuevonob() {
		return nuevonob;
	}

	public void setNuevonob(String nuevonob) {
		this.nuevonob = nuevonob;
	}

	public String getActualesca() {
		return actualesca;
	}

	public void setActualesca(String actualesca) {
		this.actualesca = actualesca;
	}

	public String getNuevoesca() {
		return nuevoesca;
	}

	public void setNuevoesca(String nuevoesca) {
		this.nuevoesca = nuevoesca;
	}

	public String getActualtec() {
		return actualtec;
	}

	public void setActualtec(String actualtec) {
		this.actualtec = actualtec;
	}

	public String getNuevotec() {
		return nuevotec;
	}

	public void setNuevotec(String nuevotec) {
		this.nuevotec = nuevotec;
	}

	public String getActualimp() {
		return actualimp;
	}

	public void setActualimp(String actualimp) {
		this.actualimp = actualimp;
	}

	public String getNuevoimp() {
		return nuevoimp;
	}

	public void setNuevoimp(String nuevoimp) {
		this.nuevoimp = nuevoimp;
	}

	public String getNuevoso() {
		return nuevoso;
	}

	public void setNuevoso(String nuevoso) {
		this.nuevoso = nuevoso;
	}

	public String getActualso() {
		return actualso;
	}

	public void setActualso(String actualso) {
		this.actualso = actualso;
	}

	public String getNuevofab() {
		return nuevofab;
	}

	public void setNuevofab(String nuevofab) {
		this.nuevofab = nuevofab;
	}

	public String getActualfab() {
		return actualfab;
	}

	public void setActualfab(String actualfab) {
		this.actualfab = actualfab;
	}

	public String getNuevopaq() {
		return nuevopaq;
	}

	public void setNuevopaq(String nuevopaq) {
		this.nuevopaq = nuevopaq;
	}

	public String getActualpaq() {
		return actualpaq;
	}

	public void setActualpaq(String actualpaq) {
		this.actualpaq = actualpaq;
	}

	//
	public String getNuevaarea() {
		return nuevaarea;
	}

	public void setNuevaarea(String nuevaarea) {
		this.nuevaarea = nuevaarea;
	}

	public String getActualarea() {
		return actualarea;
	}

	public void setActualarea(String actualarea) {
		this.actualarea = actualarea;
	}

	public String getNuevodepa() {
		return nuevodepa;
	}

	public void setNuevodepa(String nuevodepa) {
		this.nuevodepa = nuevodepa;
	}

	public String getActualdepa() {
		return actualdepa;
	}

	public void setActualdepa(String actualdepa) {
		this.actualdepa = actualdepa;
	}

	public String getNuevoprs() {
		return nuevoprs;
	}

	public void setNuevoprs(String nuevoprs) {
		this.nuevoprs = nuevoprs;
	}

	public String getActualprs() {
		return actualprs;
	}

	public void setActualprs(String actualprs) {
		this.actualprs = actualprs;
	}

	public List<Monitor> getMonitors1() {
		List<Monitor> mon = new ArrayList<Monitor>();
		FacesMessage msg = new FacesMessage();

		try {
			mon = equipoService.getMonitors();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar monitores!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return mon;
	}

	public List<Monitor> getMonitors2() {
		List<Monitor> mon = new ArrayList<Monitor>();
		FacesMessage msg = new FacesMessage();

		try {
			mon = equipoService.getMonitors();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar monitores!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return mon;
	}

	public List<Mouse> getMouses1() {
		List<Mouse> mou = new ArrayList<Mouse>();
		FacesMessage msg = new FacesMessage();

		try {
			mou = equipoService.getMouses();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar mouses!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return mou;
	}

	public List<Mouse> getMouses2() {
		List<Mouse> mou = new ArrayList<Mouse>();
		FacesMessage msg = new FacesMessage();

		try {
			mou = equipoService.getMouses();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar mouses!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return mou;
	}

	public List<Nobreak> getNobreaks1() {
		List<Nobreak> nob = new ArrayList<Nobreak>();
		FacesMessage msg = new FacesMessage();

		try {
			nob = equipoService.getNobreaks();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar nobreaks!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return nob;
	}

	public List<Nobreak> getNobreaks2() {
		List<Nobreak> nob = new ArrayList<Nobreak>();
		FacesMessage msg = new FacesMessage();

		try {
			nob = equipoService.getNobreaks();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar nobreaks!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return nob;
	}

	public List<Scanner> getEscaners1() {
		List<Scanner> esca = new ArrayList<Scanner>();
		FacesMessage msg = new FacesMessage();

		try {
			esca = equipoService.getEscaners();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar escaners!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return esca;
	}

	public List<Scanner> getEscaners2() {
		List<Scanner> esca = new ArrayList<Scanner>();
		FacesMessage msg = new FacesMessage();

		try {
			esca = equipoService.getEscaners();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar escaners!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return esca;
	}

	public List<Teclado> getTeclados1() {
		List<Teclado> tec = new ArrayList<Teclado>();
		FacesMessage msg = new FacesMessage();

		try {
			tec = equipoService.getTeclados();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar teclados!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return tec;
	}

	public List<Teclado> getTeclados2() {
		List<Teclado> tec = new ArrayList<Teclado>();
		FacesMessage msg = new FacesMessage();

		try {
			tec = equipoService.getTeclados();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar teclados!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return tec;
	}

	public List<Impresora> getImpresoras1() {
		List<Impresora> imp = new ArrayList<Impresora>();
		FacesMessage msg = new FacesMessage();

		try {
			imp = equipoService.getImpresoras();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar impresoras!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return imp;
	}

	public List<Impresora> getImpresoras2() {
		List<Impresora> imp = new ArrayList<Impresora>();
		FacesMessage msg = new FacesMessage();

		try {
			imp = equipoService.getImpresoras();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar impresoras!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return imp;
	}

	public List<Sistemaoperativo> getSos1() {
		List<Sistemaoperativo> sos = new ArrayList<Sistemaoperativo>();
		FacesMessage msg = new FacesMessage();

		try {
			sos = equipoService.getSos();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar sistema operativo!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return sos;
	}

	public List<Sistemaoperativo> getSos2() {
		List<Sistemaoperativo> sos = new ArrayList<Sistemaoperativo>();
		FacesMessage msg = new FacesMessage();

		try {
			sos = equipoService.getSos();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar sistema operativo!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return sos;
	}

	public List<Area> getArea1() {
		List<Area> ars = new ArrayList<Area>();
		FacesMessage msg = new FacesMessage();

		try {
			ars = equipoService.getArea();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar areas!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return ars;
	}

	public List<Area> getArea2() {
		List<Area> ars = new ArrayList<Area>();
		FacesMessage msg = new FacesMessage();

		try {
			ars = equipoService.getArea();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar areas!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return ars;
	}

	public List<Departamento> getDepa1() {
		List<Departamento> depas = new ArrayList<Departamento>();
		FacesMessage msg = new FacesMessage();

		try {
			depas = equipoService.getDepas();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar departamentos!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return depas;
	}

	public List<Departamento> getDepa2() {
		List<Departamento> depas = new ArrayList<Departamento>();
		FacesMessage msg = new FacesMessage();

		try {
			depas = equipoService.getDepas();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar departamentos!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return depas;
	}

	public List<Personal> getPrs1() {
		List<Personal> prs = new ArrayList<Personal>();
		FacesMessage msg = new FacesMessage();

		try {
			prs = equipoService.getPrs();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar personal!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return prs;
	}

	public List<Personal> getPrs2() {
		List<Personal> prs = new ArrayList<Personal>();
		FacesMessage msg = new FacesMessage();

		try {
			prs = equipoService.getPrs();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar personal!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return prs;
	}

	public List<Fabricante> getFabs1() {
		List<Fabricante> fabs = new ArrayList<Fabricante>();
		FacesMessage msg = new FacesMessage();

		try {
			fabs = equipoService.getFabs();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar fabricante!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return fabs;
	}

	public List<Fabricante> getFabs2() {
		List<Fabricante> fabs = new ArrayList<Fabricante>();
		FacesMessage msg = new FacesMessage();

		try {
			fabs = equipoService.getFabs();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar fabricante!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return fabs;
	}

	public List<Paqueteria> getPaqs1() {
		List<Paqueteria> paqs = new ArrayList<Paqueteria>();
		FacesMessage msg = new FacesMessage();

		try {
			paqs = equipoService.getPaqs();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar paqueteria!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return paqs;
	}

	public List<Paqueteria> getPaqs2() {
		List<Paqueteria> paqs = new ArrayList<Paqueteria>();
		FacesMessage msg = new FacesMessage();

		try {
			paqs = equipoService.getPaqs();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar paqueteria!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return paqs;
	}

	public void llenaEquipo() throws Exception {
		try {
			listaequipo = equipoService.getEquipo();

			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"equipo?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setListaequipo(listaequipo);
	}

	public void llenaEquipo1() throws Exception {
		try {
			listaequipo = equipoService.getEquipo();

			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"reporte1?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setListaequipo(listaequipo);
	}
	
	public void llenaEquipo2() throws Exception {
		try {
			listaequipo = equipoService.getEquipo();

			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"reporte2?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setListaequipo(listaequipo);
	}
	
	public void llenaEquipo3() throws Exception {
		try {
			listaequipo = equipoService.getEquipo();

			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"reporte3?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setListaequipo(listaequipo);
	}
	
	public void llenaEquipo4() throws Exception {
		try {
			listaequipo = equipoService.getEquipob();

			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"reporte4?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setListaequipo(listaequipo);
	}
	
	public void llenaEquipo5() throws Exception {
		try {
			listaequipo = equipoService.getEquipob();

			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"reporte5?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setListaequipo(listaequipo);
	}
	
	public void llenaEquipo6() throws Exception {
		try {
			listaequipo = equipoService.getEquipob();

			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"reporte6?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setListaequipo(listaequipo);
	}

	public void llenaEquipo7() throws Exception {
		try {
			listaequipo = equipoService.getEquipo();

			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"movimientos2?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setListaequipo(listaequipo);
	}
	
	public void llenaEquipo8() throws Exception {
		try {
			listaequipo = equipoService.getEquipo();

			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"movimientos3?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setListaequipo(listaequipo);
	}
	
	public void llenaEquipo9() throws Exception {
		try {
			listaequipo = equipoService.getEquipo();

			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"reportefechas?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setListaequipo(listaequipo);
	}
	
	public void resetEquipo() {
		setFecha(null);
		setNo_serie("");
		setModelo("");
		setNombre_equipo("");
		setAntivirus(null);
		setIp(null);
		setNuevomon(null);
		setNuevomou(null);
		setNuevonob(null);
		setNuevoesca(null);
		setNuevotec(null);
		setNuevoimp(null);
		setNuevoso(null);
		setNuevofab(null);
		setNuevopaq(null);
		setNuevaarea(null);
		setNuevodepa(null);
		setNuevoprs(null);
		setObservaciones("");
	}

	private Movimientos mov;
	private Date fecha1;
	
	public Date getFecha1() {
		return fecha1;
	}

	public void setFecha1(Date fecha1) {
		this.fecha1 = fecha1;
	}

	public void altaEquipo() throws Exception {
		System.out.println(no_serie);
		try {
			if(equipo == null) {
			Equipo equipo = new Equipo();
			equipo.setFecha(fecha);
			equipo.setNo_serie(no_serie);
			equipo.setModelo(modelo);
			equipo.setNombre_equipo(nombre_equipo);
			equipo.setAntivirus(antivirus);
			equipo.setIp(ip);
			equipo.setMonitor(equipoService.getMonByKey(nuevomon));
			equipo.setMouse(equipoService.getMouByKey(nuevomou));
			equipo.setNobreak(equipoService.getNobByKey(nuevonob));
			equipo.setScanner(equipoService.getEscaByKey(nuevoesca));
			equipo.setTeclado(equipoService.getTecByKey(nuevotec));
			equipo.setImpresora(equipoService.getImpByKey(nuevoimp));	
			equipo.setSistemaoperativo(equipoService.getSoByKey(nuevoso));
			equipo.setFabricante(equipoService.getFabByKey(nuevofab));
			equipo.setPaqueteria(equipoService.getPaqByKey(nuevopaq));
			equipo.setArea(equipoService.getAreaByKey(nuevaarea));
			equipo.setDepartamento(equipoService.getDepaByKey(nuevodepa));
			equipo.setPersonal(equipoService.getPrsByKey(nuevoprs));
			equipo.setObservaciones(observaciones);
			equipo.setEstatus(1);
			
			mov = new Movimientos();
			mov.setArea(equipoService.getAreaByKey(nuevaarea));
			mov.setDepartamento(equipoService.getDepaByKey(nuevodepa));
			mov.setPersonal(equipoService.getPrsByKey(nuevoprs));
			mov.setFecha(fecha);
			mov.setDescripcion(observaciones);
			
			movimientosService.altaMov(mov);
			equipo = equipoService.altaEquipo(equipo, mov);
			//equipoService.altaEquipo(equipo);
			}
			else {
				equipo.setFecha(fecha);
				equipo.setNo_serie(no_serie);
				equipo.setModelo(modelo);
				equipo.setNombre_equipo(nombre_equipo);
				equipo.setAntivirus(antivirus);
				equipo.setIp(ip);
				equipo.setMonitor(equipoService.getMonByKey(nuevomon));
				equipo.setMouse(equipoService.getMouByKey(nuevomou));
				equipo.setNobreak(equipoService.getNobByKey(nuevonob));
				equipo.setScanner(equipoService.getEscaByKey(nuevoesca));
				equipo.setTeclado(equipoService.getTecByKey(nuevotec));
				equipo.setImpresora(equipoService.getImpByKey(nuevoimp));
				equipo.setSistemaoperativo(equipoService.getSoByKey(nuevoso));
				equipo.setFabricante(equipoService.getFabByKey(nuevofab));
				equipo.setPaqueteria(equipoService.getPaqByKey(nuevopaq));
				equipo.setArea(equipoService.getAreaByKey(nuevaarea));
				equipo.setDepartamento(equipoService.getDepaByKey(nuevodepa));
				equipo.setPersonal(equipoService.getPrsByKey(nuevoprs));
				equipo.setObservaciones(observaciones);
				equipo.setEstatus(1);
				
				//mov = equipo.getMovimiento()
				mov.setArea(equipoService.getAreaByKey(nuevaarea));
				mov.setDepartamento(equipoService.getDepaByKey(nuevodepa));
				mov.setPersonal(equipoService.getPrsByKey(nuevoprs));
				mov.setFecha(fecha);
				mov.setDescripcion(observaciones);
				movimientosService.altaMov(mov);
				equipo = equipoService.altaEquipo(equipo, mov);

				//equipoService.altaEquipo(equipo);
				
			}
			llenaEquipo();
			resetEquipo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void actualizar() throws Exception {
		try {
			Equipo eq = new Equipo();			
			Equipo eq1 = new Equipo();
			eq1.setFecha(eq.getFecha());
			eq1.setNo_serie(no_serie);
			eq1.setModelo(modelo);
			eq1.setNombre_equipo(nombre_equipo);
			eq1.setAntivirus(antivirus);
			eq1.setIp(ip);
			eq1.setObservaciones(observaciones);

			// combos
			
			eq1.setEstatus(eq.getEstatus());
			if (estatus != null) {
				eq1.setEstatus(estatus);
			} 
			
			eq1.setMonitor(eq.getMonitor());
			if (actualmon != null) {
				eq1.setMonitor(equipoService.getMonByKey(actualmon));
			} else {
				eq1.setMonitor(eq.getMonitor());
			}

			eq1.setMouse(eq.getMouse());
			if (actualmou != null) {
				eq1.setMouse(equipoService.getMouByKey(actualmou));
			} else {
				eq1.setMouse(eq.getMouse());
			}

			eq1.setNobreak(eq.getNobreak());
			if (actualnob != null) {
				eq1.setNobreak(equipoService.getNobByKey(actualnob));
			} else {
				eq1.setNobreak(eq.getNobreak());
			}

			eq1.setScanner(eq.getScanner());
			if (actualesca != null) {
				eq1.setScanner(equipoService.getEscaByKey(actualesca));
			} else {
				eq1.setScanner(eq.getScanner());
			}

			eq1.setTeclado(eq.getTeclado());
			if (actualtec != null) {
				eq1.setTeclado(equipoService.getTecByKey(actualtec));
			} else {
				eq1.setTeclado(eq.getTeclado());
			}

			eq1.setImpresora(eq.getImpresora());
			if (actualimp != null) {
				eq1.setImpresora(equipoService.getImpByKey(actualimp));
			} else {
				eq1.setImpresora(eq.getImpresora());
			}

			eq1.setSistemaoperativo(eq.getSistemaoperativo());
			if (actualso != null) {
				eq1.setSistemaoperativo(equipoService.getSoByKey(actualso));
			} else {
				eq1.setSistemaoperativo(eq.getSistemaoperativo());
			}

			eq1.setFabricante(eq.getFabricante());
			if (actualfab != null) {
				eq1.setFabricante(equipoService.getFabByKey(actualfab));
			} else {
				eq1.setFabricante(eq.getFabricante());
			}

			eq1.setPaqueteria(eq.getPaqueteria());
			if (actualpaq != null) {
				eq1.setPaqueteria(equipoService.getPaqByKey(actualpaq));
			} else {
				eq1.setPaqueteria(eq.getPaqueteria());
			}
			
			eq1.setArea(eq.getArea());
			if (actualarea != null) {
				eq1.setArea(equipoService.getAreaByKey(actualarea));
			} else {
				eq1.setArea(eq.getArea());
			}

			eq1.setDepartamento(eq.getDepartamento());
			if (actualdepa != null) {
				eq1.setDepartamento(equipoService.getDepaByKey(actualdepa));
			} else {
				eq1.setDepartamento(eq.getDepartamento());
			}

			eq1.setPersonal(eq.getPersonal());
			if (actualprs != null) {
				eq1.setPersonal(equipoService.getPrsByKey(actualprs));
			} else {
				eq1.setPersonal(eq.getPersonal());
			}

			equipoService.modificarEquipo(eq1);
			llenaEquipo8();
		
			
			llenaEquipo();
			resetEquipo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	///////calendario
	public void click() {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.update("form2:display");
		requestContext.execute("PF('dlg').show()");
	}
	  
	  public void onRowCancel(RowEditEvent event) {
			Equipo prod = (Equipo) event.getObject();
			FacesMessage msg = new FacesMessage("Movimiento Cancelado",
					prod.getNo_serie());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	  public void onRowEditEquipo(RowEditEvent event) {
			System.out.println(actualarea);
			System.out.println(actualdepa);
			System.out.println(actualprs);

				Equipo eq = (Equipo) event.getObject();
				FacesMessage msg = new FacesMessage("Movimiento Editado",
						eq.getNo_serie());
		        FacesContext.getCurrentInstance().addMessage(null, msg);
				try {
					
					Equipo eq1 = new Equipo();
					eq1.setId_equipo(eq.getId_equipo());
					eq1.setFecha(fecha1);
					eq1.setNo_serie(eq.getNo_serie());
					eq1.setModelo(eq.getModelo());
					eq1.setNombre_equipo(eq.getNombre_equipo());
					eq1.setAntivirus(eq.getAntivirus());
					eq1.setIp(eq.getIp());
					eq1.setObservaciones(eq.getObservaciones());

					// combos
					eq1.setEstatus(eq.getEstatus());
					if (estatus != null) {
						eq1.setEstatus(estatus);
					} 
					
					eq1.setMonitor(eq.getMonitor());
					if (actualmon != null) {
						eq1.setMonitor(equipoService.getMonByKey(actualmon));
					} else {
						eq1.setMonitor(eq.getMonitor());
					}

					eq1.setMouse(eq.getMouse());
					if (actualmou != null) {
						eq1.setMouse(equipoService.getMouByKey(actualmou));
					} else {
						eq1.setMouse(eq.getMouse());
					}

					eq1.setNobreak(eq.getNobreak());
					if (actualnob != null) {
						eq1.setNobreak(equipoService.getNobByKey(actualnob));
					} else {
						eq1.setNobreak(eq.getNobreak());
					}

					eq1.setScanner(eq.getScanner());
					if (actualesca != null) {
						eq1.setScanner(equipoService.getEscaByKey(actualesca));
					} else {
						eq1.setScanner(eq.getScanner());
					}

					eq1.setTeclado(eq.getTeclado());
					if (actualtec != null) {
						eq1.setTeclado(equipoService.getTecByKey(actualtec));
					} else {
						eq1.setTeclado(eq.getTeclado());
					}

					eq1.setImpresora(eq.getImpresora());
					if (actualimp != null) {
						eq1.setImpresora(equipoService.getImpByKey(actualimp));
					} else {
						eq1.setImpresora(eq.getImpresora());
					}

					eq1.setSistemaoperativo(eq.getSistemaoperativo());
					if (actualso != null) {
						eq1.setSistemaoperativo(equipoService.getSoByKey(actualso));
					} else {
						eq1.setSistemaoperativo(eq.getSistemaoperativo());
					}

					eq1.setFabricante(eq.getFabricante());
					if (actualfab != null) {
						eq1.setFabricante(equipoService.getFabByKey(actualfab));
					} else {
						eq1.setFabricante(eq.getFabricante());
					}

					eq1.setPaqueteria(eq.getPaqueteria());
					if (actualpaq != null) {
						eq1.setPaqueteria(equipoService.getPaqByKey(actualpaq));
					} else {
						eq1.setPaqueteria(eq.getPaqueteria());
					}
					
					eq1.setArea(eq.getArea());
					if (actualarea != null) {
						eq1.setArea(equipoService.getAreaByKey(actualarea));
					} else {
						eq1.setArea(eq.getArea());
					}

					eq1.setDepartamento(eq.getDepartamento());
					if (actualdepa != null) {
						eq1.setDepartamento(equipoService.getDepaByKey(actualdepa));
					} else {
						eq1.setDepartamento(eq.getDepartamento());
					}

					eq1.setPersonal(eq.getPersonal());
					if (actualprs != null) {
						eq1.setPersonal(equipoService.getPrsByKey(actualprs));
					} else {
						eq1.setPersonal(eq.getPersonal());
					}
					
					mov = new Movimientos();
					mov.setArea(equipoService.getAreaByKey(actualarea));
					mov.setDepartamento(equipoService.getDepaByKey(actualdepa));
					mov.setPersonal(equipoService.getPrsByKey(actualprs));
					mov.setFecha(fecha1);
					mov.setDescripcion(eq.getObservaciones());
					
					movimientosService.altaMov(mov);
					equipoService.modificarEquipoRow(eq1, mov);
					
					actualso = (null);
					actualfab = (null);
					actualpaq = (null);
					actualarea = (null);
					actualdepa = (null);
					actualprs = (null);
					
					fecha1 = (null);
					observaciones=("");
					
					
					llenaEquipo7();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
	public void onCellEditEquipo(CellEditEvent event) {
		System.out.println(actualmon);
		System.out.println(actualmou);
		System.out.println(actualnob);
		System.out.println(actualesca);
		System.out.println(actualtec);
		System.out.println(actualimp);
		System.out.println(actualso);
		System.out.println(actualfab);
		System.out.println(actualpaq);
		System.out.println(actualarea);
		System.out.println(actualdepa);
		System.out.println(actualprs);

		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		if (newValue != null && !newValue.equals(oldValue)) {
			DataTable p = (DataTable) event.getSource();
			Equipo eq = (Equipo) p.getRowData();
			try {
				Equipo eq1 = new Equipo();
				eq1.setFecha(eq.getFecha());
				eq1.setNo_serie(eq.getNo_serie());
				eq1.setModelo(eq.getModelo());
				eq1.setNombre_equipo(eq.getNombre_equipo());
				eq1.setAntivirus(eq.getAntivirus());
				eq1.setIp(eq.getIp());
				eq1.setObservaciones(eq.getObservaciones());

				eq1.setEstatus(eq.getEstatus());
				if (estatus != null) {
					eq1.setEstatus(estatus);
				} 
				
				// combos
				eq1.setMonitor(eq.getMonitor());
				if (actualmon != null) {
					eq1.setMonitor(equipoService.getMonByKey(actualmon));
				} else {
					eq1.setMonitor(eq.getMonitor());
				}

				eq1.setMouse(eq.getMouse());
				if (actualmou != null) {
					eq1.setMouse(equipoService.getMouByKey(actualmou));
				} else {
					eq1.setMouse(eq.getMouse());
				}

				eq1.setNobreak(eq.getNobreak());
				if (actualnob != null) {
					eq1.setNobreak(equipoService.getNobByKey(actualnob));
				} else {
					eq1.setNobreak(eq.getNobreak());
				}

				eq1.setScanner(eq.getScanner());
				if (actualesca != null) {
					eq1.setScanner(equipoService.getEscaByKey(actualesca));
				} else {
					eq1.setScanner(eq.getScanner());
				}

				eq1.setTeclado(eq.getTeclado());
				if (actualtec != null) {
					eq1.setTeclado(equipoService.getTecByKey(actualtec));
				} else {
					eq1.setTeclado(eq.getTeclado());
				}

				eq1.setImpresora(eq.getImpresora());
				if (actualimp != null) {
					eq1.setImpresora(equipoService.getImpByKey(actualimp));
				} else {
					eq1.setImpresora(eq.getImpresora());
				}

				eq1.setSistemaoperativo(eq.getSistemaoperativo());
				if (actualso != null) {
					eq1.setSistemaoperativo(equipoService.getSoByKey(actualso));
				} else {
					eq1.setSistemaoperativo(eq.getSistemaoperativo());
				}

				eq1.setFabricante(eq.getFabricante());
				if (actualfab != null) {
					eq1.setFabricante(equipoService.getFabByKey(actualfab));
				} else {
					eq1.setFabricante(eq.getFabricante());
				}

				eq1.setPaqueteria(eq.getPaqueteria());
				if (actualpaq != null) {
					eq1.setPaqueteria(equipoService.getPaqByKey(actualpaq));
				} else {
					eq1.setPaqueteria(eq.getPaqueteria());
				}

				eq1.setArea(eq.getArea());
				if (actualarea != null) {
					eq1.setArea(equipoService.getAreaByKey(actualarea));
				} else {
					eq1.setArea(eq.getArea());
				}

				eq1.setDepartamento(eq.getDepartamento());
				if (actualdepa != null) {
					eq1.setDepartamento(equipoService.getDepaByKey(actualdepa));
				} else {
					eq1.setDepartamento(eq.getDepartamento());
				}

				eq1.setPersonal(eq.getPersonal());
				if (actualprs != null) {
					eq1.setPersonal(equipoService.getPrsByKey(actualprs));
				} else {
					eq1.setPersonal(eq.getPersonal());
				}

				equipoService.modificarEquipo(eq1);
				actualmon = (null);
				actualmou = (null);
				actualnob = (null);
				actualesca = (null);
				actualtec = (null);
				actualimp = (null);

				actualso = (null);
				actualfab = (null);
				actualpaq = (null);
				actualarea = (null);
				actualdepa = (null);
				actualprs = (null);
				
				estatus=(null);

				llenaEquipo();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void borrarEquipo() {
		Equipo eq = selectedEquipo;
		try {
			equipoService.borrarEquipo(eq);
			llenaEquipo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String fileName;
	
	public String getFileName() {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmss");
        fileName = "Reporte-Equipos".concat(formatter.format(new Date()));

        return fileName;
    }
	
	 public void postProcessXLS(Object document) {
	        HSSFWorkbook wb = (HSSFWorkbook) document;
	        HSSFSheet sheet = wb.getSheetAt(0);
	        HSSFRow header = sheet.getRow(0);
	         
	        HSSFCellStyle cellStyle = wb.createCellStyle(); 
	        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
	        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	         
	        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
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

		final Phrase phrase = new Phrase("Reporte Equipos");
		pdf.add(Image.getInstance(logo));
		pdf.add(phrase);
	}

	public void postProcessPDF(Object document) throws IOException,
			DocumentException {
		final Document pdf = (Document) document;
		pdf.setPageSize(PageSize.A4.rotate());

	}

}
