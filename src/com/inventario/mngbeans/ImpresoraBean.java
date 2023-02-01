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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inventario.model.Fabricante;
import com.inventario.model.Impresora;
import com.inventario.model.Teclado;
import com.inventario.service.ImpresoraService;
import com.inventario.service.EquipoService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;

@Component
@ManagedBean
@SessionScoped
public class ImpresoraBean implements Serializable {

	private static final long serialVersionUID = -6979289764021788496L;

	@Autowired
	ImpresoraService impresoraService;

	private Impresora impre;
	private String serie;
	private String modelo;
	private String tipo;
	private List<Impresora> listaImpresoras;
	private Impresora selectedImpre;

	private String actualfab;
	private String nuevofab;
	
	@Autowired
	EquipoService equipoService;

	public Impresora getImpre() {
		return impre;
	}

	public void setImpre(Impresora impre) {
		this.impre = impre;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Impresora> getListaImpresoras() {
		return listaImpresoras;
	}

	public void setlistaImpresoras(List<Impresora> listaImpresoras) {
		this.listaImpresoras = listaImpresoras;
	}

	public String getActualfab() {
		return actualfab;
	}

	public void setActualfab(String actualfab) {
		this.actualfab = actualfab;
	}

	public String getNuevofab() {
		return nuevofab;
	}

	public void setNuevofab(String nuevofab) {
		this.nuevofab = nuevofab;
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
	
	public void resetImpre() {
		setSerie("");
		setModelo("");
		setTipo("");
		setNuevofab("");
	}

	public void altaImpre() throws Exception {
		try {
			Impresora impre = new Impresora();
			impre.setSerie(serie);
			impre.setModelo(modelo);
			impre.setTipo(tipo);
			impre.setFabricante(equipoService.getFabByKey(nuevofab));
			impresoraService.altaImpre(impre);
			llenaImpre();
			resetImpre();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onCellEditImpre(CellEditEvent event) {
		
		System.out.println(actualfab);
		System.out.println(tipo);
		
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		if (newValue != null && !newValue.equals(oldValue)) {
			DataTable p = (DataTable) event.getSource();
			Impresora impre = (Impresora) p.getRowData();
			try {
				Impresora impre1 = new Impresora();
				impre1.setId_impresora(impre.getId_impresora());
				impre1.setSerie(impre.getSerie());
				impre1.setModelo(impre.getModelo());
				impre1.setTipo(tipo);
				impre1.setFabricante(impre.getFabricante());
				if (actualfab != null) {
					impre1.setFabricante(equipoService.getFabByKey(actualfab));
				} else {
					impre1.setFabricante(impre.getFabricante());
				}
				
				impresoraService.modificarImpre(impre1);
				
				actualfab = (null);
				
				llenaImpre();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void borrarImpre() {
		Impresora impre = selectedImpre;
		try {
			Impresora impre1 = new Impresora();
			impre1.setId_impresora(impre.getId_impresora());
			impresoraService.borrarImpre(impre);
			llenaImpre();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void llenaImpre() throws Exception {
		try {
			listaImpresoras = impresoraService.getImpresora();
			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"impresora?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setlistaImpresoras(listaImpresoras);

	}

	public Impresora getSelectedImpre() {
		return selectedImpre;
	}

	public void setSelectedImpre(Impresora selectedImpre) {
		this.selectedImpre = selectedImpre;
	}
	
private String fileName;
	
	public String getFileName() {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmss");
        fileName = "Reporte-Impresoras".concat(formatter.format(new Date()));

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

		final Phrase phrase = new Phrase("Reporte Impresoras");
		pdf.add(Image.getInstance(logo));
		pdf.add(phrase);
	}

	public void postProcessPDF(Object document) throws IOException,
			DocumentException {
		final Document pdf = (Document) document;
		pdf.setPageSize(PageSize.A4.rotate());

	}
	
	////////Teclado

	private Teclado tecla;
	private String seriete;
	private String modelote;
	private List<Teclado> listaTeclados;
	private Teclado selectedTecla;

	private String actualfab1;
	private String nuevofab1;
	
	public Teclado getTecla() {
		return tecla;
	}

	public void setTecla(Teclado tecla) {
		this.tecla = tecla;
	}

	public String getSeriete() {
		return seriete;
	}

	public void setSeriete(String seriete) {
		this.seriete = seriete;
	}

	public String getModelote() {
		return modelote;
	}

	public void setModelote(String modelote) {
		this.modelote = modelote;
	}

	public List<Teclado> getListaTeclados() {
		return listaTeclados;
	}

	public void setListaTeclados(List<Teclado> listaTeclados) {
		this.listaTeclados = listaTeclados;
	}

	public Teclado getSelectedTecla() {
		return selectedTecla;
	}

	public void setSelectedTecla(Teclado selectedTecla) {
		this.selectedTecla = selectedTecla;
	}

	public String getActualfab1() {
		return actualfab1;
	}

	public void setActualfab1(String actualfab1) {
		this.actualfab1 = actualfab1;
	}

	public String getNuevofab1() {
		return nuevofab1;
	}

	public void setNuevofab1(String nuevofab1) {
		this.nuevofab1 = nuevofab1;
	}
	
	public void resetTecla() {
		setSeriete("");
		setModelote("");
		setNuevofab1("");
	}

	public void altaTecla() throws Exception {
		System.out.println(seriete);
		System.out.println(modelote);
		System.out.println(nuevofab1);


		try {
			Teclado tecla = new Teclado();
			tecla.setSerie(seriete);
			tecla.setModelo(modelote);
			tecla.setFabricante(equipoService.getFabByKey(nuevofab1));
			impresoraService.altaTecla(tecla);
			llenaTecla();
			resetTecla();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onCellEditTecla(CellEditEvent event) {
		
		System.out.println(actualfab1);
		
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		if (newValue != null && !newValue.equals(oldValue)) {
			DataTable p = (DataTable) event.getSource();
			Teclado tecla = (Teclado) p.getRowData();
			try {
				Teclado tecla1 = new Teclado();
				tecla1.setId_teclado(tecla.getId_teclado());
				tecla1.setSerie(tecla.getSerie());
				tecla1.setModelo(tecla.getModelo());
				tecla1.setFabricante(tecla.getFabricante());
				if (actualfab != null) {
					tecla1.setFabricante(equipoService.getFabByKey(actualfab1));
				} else {
					tecla1.setFabricante(tecla.getFabricante());
				}
				
				impresoraService.modificarTecla(tecla1);
				
				actualfab1 = (null);
				
				llenaTecla();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void borrarTecla() {
		Teclado tecla = selectedTecla;
		try {
			Teclado tecla1 = new Teclado();
			tecla1.setId_teclado(tecla.getId_teclado());
			impresoraService.borrarTecla(tecla);
			llenaTecla();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void llenaTecla() throws Exception {
		try {
			listaTeclados = impresoraService.getTeclado();
			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"teclado?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setListaTeclados(listaTeclados);

	}
	
private String fileName1;
	
	public String getFileName1() {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmss");
        fileName1 = "Reporte-Teclados".concat(formatter.format(new Date()));

        return fileName1;
    }
	
	 public void postProcessXLS1(Object document) {
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


	public void preProcessPDF1(Object document) throws IOException,
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

		final Phrase phrase = new Phrase("Reporte Teclados");
		pdf.add(Image.getInstance(logo));
		pdf.add(phrase);
	}

	public void postProcessPDF1(Object document) throws IOException,
			DocumentException {
		final Document pdf = (Document) document;
		pdf.setPageSize(PageSize.A4.rotate());

	}

}
