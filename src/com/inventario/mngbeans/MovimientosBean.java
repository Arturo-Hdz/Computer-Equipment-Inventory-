package com.inventario.mngbeans;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inventario.model.Equipo;
import com.inventario.model.Area;
import com.inventario.model.Departamento;
import com.inventario.model.Movimientos;
import com.inventario.model.Tipomantenimiento;
import com.inventario.service.PaqueteriaService;
import com.inventario.service.MovimientosService;

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
public class MovimientosBean implements Serializable {

	private static final long serialVersionUID = -5319428289796310531L;

	@Autowired
	MovimientosService movimientosService;
	
/*	@Autowired
	ClienteService clienteService;

	@Autowired
	TamanoService tamanoService;

	@Autowired
	ProductoService productoService;

	@Autowired
	IngredienteService ingredienteService;

	@Autowired
	CategoriaService categoriaService;
*/

	private Area area;
	private String nombre_area;
	private List<Area> listaAreas;
	private Area selectedArea;

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getNombre_area() {
		return nombre_area;
	}

	public void setNombre_area(String nombre_area) {
		this.nombre_area= nombre_area;
	}

	public List<Area> getListaAreas() {
		return listaAreas;
	}

	public void setlistaAreas(List<Area> listaAreas) {
		this.listaAreas = listaAreas;
	}
	
	public void resetArea() {
		setNombre_area("");
	}


	public void altaArea() throws Exception {
		try {
			Area area = new Area();
			area.setNombre_area(nombre_area);
			movimientosService.altaArea(area);
			llenaArea();
			resetArea();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onCellEditArea(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		if (newValue != null && !newValue.equals(oldValue)) {
			DataTable p = (DataTable) event.getSource();
			Area area = (Area) p.getRowData();
			try {
				Area area1 = new Area();
				area1.setId_area(area.getId_area());
				area1.setNombre_area(area.getNombre_area());
				movimientosService.modificarArea(area1);
				llenaArea();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void borrarArea() {
		Area area = selectedArea;
		try {
			Area area1 = new Area();
			area1.setId_area(area.getId_area());
			movimientosService.borrarArea(area);
			llenaArea();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void llenaArea() throws Exception {
		try {
			listaAreas = movimientosService.getArea();
			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"area?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setlistaAreas(listaAreas);

	}
	
	public Area getSelectedArea() {
		return selectedArea;
	}

	public void setSelectedArea(Area selectedArea) {
		this.selectedArea = selectedArea;
	}
	
	///////Departamento

	private Departamento departamento;
	private String nombre_departamento;
	private List<Departamento> listaDepas;
	private Departamento selectedDepas;
	
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getNombre_departamento() {
		return nombre_departamento;
	}

	public void setNombre_departamento(String nombre_departamento) {
		this.nombre_departamento= nombre_departamento;
	}

	public List<Departamento> getListaDepas() {
		return listaDepas;
	}

	public void setlistaDepas(List<Departamento> listaDepas) {
		this.listaDepas = listaDepas;
	}
	
	public void resetDepa() {
		setNombre_departamento("");
	}


	public void altaDepa() throws Exception {
		try {
			Departamento depa = new Departamento();
			depa.setNombre_departamento(nombre_departamento);
			movimientosService.altaDepa(depa);
			llenaDepa();
			resetDepa();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onCellEditDepa(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		if (newValue != null && !newValue.equals(oldValue)) {
			DataTable p = (DataTable) event.getSource();
			Departamento depa = (Departamento) p.getRowData();
			try {
				Departamento depa1 = new Departamento();
				depa1.setId_departamento(depa.getId_departamento());
				depa1.setNombre_departamento(depa.getNombre_departamento());
				movimientosService.modificarDepa(depa1);
				llenaDepa();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void borrarDepa() {
		Departamento depa = selectedDepas;
		try {
			Departamento depa1 = new Departamento();
			depa1.setId_departamento(depa.getId_departamento());
			movimientosService.borrarDepa(depa);
			llenaDepa();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void llenaDepa() throws Exception {
		try {
			listaDepas = movimientosService.getDepartamento();
			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"departamento?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setlistaDepas(listaDepas);

	}
	
	public Departamento getSelectedDepas() {
		return selectedDepas;
	}

	public void setSelectedDepas(Departamento selectedDepas) {
		this.selectedDepas = selectedDepas;
	}
	
	//////movimientos

	private Movimientos movimiento;
	private List<Movimientos> listaMovimiento;
	private Date fecha;
	private String descripcion;
	
	
	private String nuevaarea;
	private String actualarea;
	private String nuevodepa;
	private String actualdepa;
	
	private String nuevoeq;
	private String actualeq;
	
	public Movimientos getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Movimientos movimiento) {
		this.movimiento = movimiento;
	}
	
	public List<Movimientos> getListaMovimiento() {
		return listaMovimiento;
	}

	public void setListaMovimiento(List<Movimientos> listaMovimiento) {
		this.listaMovimiento = listaMovimiento;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

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
	
	public List<Equipo> getEquipos1() {
		List<Equipo> eq = new ArrayList<Equipo>();
		FacesMessage msg = new FacesMessage();

		try {
			eq = movimientosService.getEquipos();
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
			eq = movimientosService.getEquipos();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar equipos!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return eq;
	}
	
	public List<Area> getAreas1() {
		List<Area> areas = new ArrayList<Area>();
		FacesMessage msg = new FacesMessage();

		try {
			areas = movimientosService.getAreas();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar areas!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return areas;
	}
	
	public List<Area> getAreas2() {
		List<Area> areas = new ArrayList<Area>();
		FacesMessage msg = new FacesMessage();

		try {
			areas = movimientosService.getAreas();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar areas!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return areas;
	}
	
	public List<Departamento> getDepa1() {
		List<Departamento> departamentos = new ArrayList<Departamento>();
		FacesMessage msg = new FacesMessage();

		try {
			departamentos = movimientosService.getDepartamentos();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar departamentos!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return departamentos;
	}
	
	public List<Departamento> getDepa2() {
		List<Departamento> departamentos = new ArrayList<Departamento>();
		FacesMessage msg = new FacesMessage();

		try {
			departamentos = movimientosService.getDepartamentos();
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error al consultar departamentos!" + e);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return departamentos;
	}
	
	public void resetMov() {
		setNuevoeq(null);
		setNuevaarea(null);
		setNuevodepa(null);
	}
	
	public void altaMov() throws Exception {
		System.out.println(nuevoeq);

		try {
			Movimientos mov = new Movimientos();
			mov.setEquipo(movimientosService.getEqByKey(nuevoeq));
			mov.setArea(movimientosService.getAreaByKey(nuevaarea));
			mov.setDepartamento(movimientosService.getDepaByKey(nuevodepa));

			movimientosService.altaMov(mov);
			llenaMovimientos();
			resetMov();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void llenaMovimientos() throws Exception {
		try {
				listaMovimiento = movimientosService.getMovimiento();

				FacesContext context = FacesContext.getCurrentInstance();
				NavigationHandler navigationHandler = context.getApplication()
						.getNavigationHandler();
				navigationHandler.handleNavigation(context, null,
						"movimientos?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setListaMovimiento(listaMovimiento);
	}
	
	public void onCellEditMovimientos(CellEditEvent event) {
		System.out.println(actualeq);
		System.out.println(actualarea);
		System.out.println(actualdepa);		
		
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		if (newValue != null && !newValue.equals(oldValue)) {
			DataTable p = (DataTable) event.getSource();
			Movimientos mov = (Movimientos) p.getRowData();
			try {
				Movimientos mov1 = new Movimientos();
				mov1.setId_movimientos(mov.getId_movimientos());
				mov1.setEquipo(mov.getEquipo());
				mov1.setArea(mov.getArea());
				mov1.setDepartamento(mov.getDepartamento());
				if(actualeq != null){
	         		mov1.setEquipo(movimientosService.getEqByKey(actualeq));
	         		}
				else{
	         		mov1.setEquipo(mov.getEquipo());
	         		}
				if(actualarea != null){
	         		mov1.setArea(movimientosService.getAreaByKey(actualarea));
	         		}
				else{
	         		mov1.setArea(mov.getArea());
	         		}
				if(actualdepa != null){
					mov1.setDepartamento(movimientosService.getDepaByKey(actualdepa));
				}
				else{
					mov1.setDepartamento(mov.getDepartamento());
				}
				movimientosService.modificarMovimiento(mov1);
				actualeq=(null);
				actualarea=(null);
				actualdepa=(null);
				llenaMovimientos();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Movimientos> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(List<Movimientos> filtrados) {
		this.filtrados = filtrados;
	}

	private List<Movimientos> filtrados;
	
	private String fileName;
	
	public String getFileName() {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmss");
        fileName = "Reporte-Movimientos-Equipos".concat(formatter.format(new Date()));

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

		final Phrase phrase = new Phrase("Reporte Movimientos de Equipos");
		pdf.add(Image.getInstance(logo));
		pdf.add(phrase);
	}

	public void postProcessPDF(Object document) throws IOException,
			DocumentException {
		final Document pdf = (Document) document;
		pdf.setPageSize(PageSize.A4.rotate());

	}
	
}
