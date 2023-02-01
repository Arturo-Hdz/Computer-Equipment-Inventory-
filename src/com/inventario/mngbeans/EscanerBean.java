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
import com.inventario.model.Nobreak;
import com.inventario.model.Scanner;
import com.inventario.service.EscanerService;
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
public class EscanerBean implements Serializable {

	private static final long serialVersionUID = 5795007323071983633L;

	@Autowired
	EscanerService escanerService;

	private Scanner esca;
	private String seriees;
	private String modeloes;
	private List<Scanner> listaEscaners;
	private Scanner selectedEsca;

	private String actualfab2;
	private String nuevofab2;
	
	@Autowired
	EquipoService equipoService;

	public Scanner getEsca() {
		return esca;
	}

	public void setImpre(Scanner esca) {
		this.esca = esca;
	}

	public String getSeriees() {
		return seriees;
	}

	public void setSeriees(String seriees) {
		this.seriees = seriees;
	}

	public String getModeloes() {
		return modeloes;
	}

	public void setModeloes(String modeloes) {
		this.modeloes = modeloes;
	}

	public List<Scanner> getListaEscaners() {
		return listaEscaners;
	}

	public void setlistaEscaners(List<Scanner> listaEscaners) {
		this.listaEscaners = listaEscaners;
	}

	public String getActualfab2() {
		return actualfab2;
	}

	public void setActualfab2(String actualfab2) {
		this.actualfab2 = actualfab2;
	}

	public String getNuevofab2() {
		return nuevofab2;
	}

	public void setNuevofab2(String nuevofab2) {
		this.nuevofab2 = nuevofab2;
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
	
	public void resetEsca() {
		setSeriees("");
		setModeloes("");
		setNuevofab2("");
	}

	public void altaEsca() throws Exception {
		try {
			Scanner esca = new Scanner();
			esca.setSerie(seriees);
			esca.setModelo(modeloes);
			esca.setFabricante(equipoService.getFabByKey(nuevofab2));
			escanerService.altaEsca(esca);
			llenaEsca();
			resetEsca();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onCellEditEsca(CellEditEvent event) {
		
		System.out.println(actualfab2);
		
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		if (newValue != null && !newValue.equals(oldValue)) {
			DataTable p = (DataTable) event.getSource();
			Scanner esca = (Scanner) p.getRowData();
			try {
				Scanner esca1 = new Scanner();
				esca1.setId_scanner(esca.getId_scanner());
				esca1.setSerie(esca.getSerie());
				esca1.setModelo(esca.getModelo());
				esca1.setFabricante(esca.getFabricante());
				if (actualfab2 != null) {
					esca1.setFabricante(equipoService.getFabByKey(actualfab2));
				} else {
					esca1.setFabricante(esca.getFabricante());
				}
				
				escanerService.modificarEsca(esca1);
				
				actualfab2 = (null);
				
				llenaEsca();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void borrarEsca() {
		Scanner esca = selectedEsca;
		try {
			Scanner esca1 = new Scanner();
			esca1.setId_scanner(esca.getId_scanner());
			escanerService.borrarEsca(esca);
			llenaEsca();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void llenaEsca() throws Exception {
		try {
			listaEscaners = escanerService.getEscaner();
			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"escaner?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setlistaEscaners(listaEscaners);

	}

	public Scanner getSelectedEsca() {
		return selectedEsca;
	}

	public void setSelectedEsca(Scanner selectedEsca) {
		this.selectedEsca = selectedEsca;
	}
	
private String fileName;
	
	public String getFileName() {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmss");
        fileName = "Reporte-Escaners".concat(formatter.format(new Date()));

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

		final Phrase phrase = new Phrase("Reporte Escaners");
		pdf.add(Image.getInstance(logo));
		pdf.add(phrase);
	}

	public void postProcessPDF(Object document) throws IOException,
			DocumentException {
		final Document pdf = (Document) document;
		pdf.setPageSize(PageSize.A4.rotate());

	}
	
	////////Nobreak

	private Nobreak nob;
	private String serienob;
	private String modelonob;
	private List<Nobreak> listaNobreaks;
	private Nobreak selectedNobreak;

	private String actualfab3;
	private String nuevofab3;
	
	public Nobreak getNobreak() {
		return nob;
	}

	public void setNobreak(Nobreak nob) {
		this.nob = nob;
	}

	public String getSerienob() {
		return serienob;
	}

	public void setSerienob(String serienob) {
		this.serienob = serienob;
	}

	public String getModelonob() {
		return modelonob;
	}

	public void setModelonob(String modelonob) {
		this.modelonob = modelonob;
	}

	public List<Nobreak> getListaNobreaks() {
		return listaNobreaks;
	}

	public void setListaNobreaks(List<Nobreak> listaNobreaks) {
		this.listaNobreaks = listaNobreaks;
	}

	public Nobreak getSelectedNobreak() {
		return selectedNobreak;
	}

	public void setSelectedNobreak(Nobreak selectedNobreak) {
		this.selectedNobreak = selectedNobreak;
	}

	public String getActualfab3() {
		return actualfab3;
	}

	public void setActualfab3(String actualfab3) {
		this.actualfab3 = actualfab3;
	}

	public String getNuevofab3() {
		return nuevofab3;
	}

	public void setNuevofab3(String nuevofab3) {
		this.nuevofab3 = nuevofab3;
	}
	
	public void resetNob() {
		setSerienob("");
		setModelonob("");
		setNuevofab3("");
	}

	public void altaNob() throws Exception {
		System.out.println(serienob);
		System.out.println(modelonob);
		System.out.println(nuevofab3);


		try {
			Nobreak nob = new Nobreak();
			nob.setSerie(serienob);
			nob.setModelo(modelonob);
			nob.setFabricante(equipoService.getFabByKey(nuevofab3));
			escanerService.altaNob(nob);
			llenaNob();
			resetNob();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onCellEditNob(CellEditEvent event) {
		
		System.out.println(actualfab3);
		
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		if (newValue != null && !newValue.equals(oldValue)) {
			DataTable p = (DataTable) event.getSource();
			Nobreak nob = (Nobreak) p.getRowData();
			try {
				Nobreak nob1 = new Nobreak();
				nob1.setId_nobreak(nob.getId_nobreak());
				nob1.setSerie(nob.getSerie());
				nob1.setModelo(nob.getModelo());
				nob1.setFabricante(nob.getFabricante());
				if (actualfab3 != null) {
					nob1.setFabricante(equipoService.getFabByKey(actualfab3));
				} else {
					nob1.setFabricante(nob.getFabricante());
				}
				
				escanerService.modificarNob(nob1);
				
				actualfab3 = (null);
				
				llenaNob();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void borrarNob() {
		Nobreak nob = selectedNobreak;
		try {
			Nobreak nob1 = new Nobreak();
			nob1.setId_nobreak(nob.getId_nobreak());
			escanerService.borrarNob(nob);
			llenaNob();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void llenaNob() throws Exception {
		try {
			listaNobreaks = escanerService.getNobreak();
			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"nobreak?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setListaNobreaks(listaNobreaks);

	}
	
private String fileName1;
	
	public String getFileName1() {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmss");
        fileName1 = "Reporte-Nobreaks".concat(formatter.format(new Date()));

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

		final Phrase phrase = new Phrase("Reporte Nobreaks");
		pdf.add(Image.getInstance(logo));
		pdf.add(phrase);
	}

	public void postProcessPDF1(Object document) throws IOException,
			DocumentException {
		final Document pdf = (Document) document;
		pdf.setPageSize(PageSize.A4.rotate());

	}

}
