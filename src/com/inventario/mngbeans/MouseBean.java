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
import com.inventario.model.Monitor;
import com.inventario.model.Mouse;
import com.inventario.service.MouseService;
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
public class MouseBean implements Serializable {

	private static final long serialVersionUID = 8890672688812867548L;

	@Autowired
	MouseService mouseService;

	private Mouse mouse;
	private String seriemou;
	private String modelomou;
	private List<Mouse> listaMouses;
	private Mouse selectedMou;

	private String actualfab4;
	private String nuevofab4;
	
	@Autowired
	EquipoService equipoService;

	public Mouse getMouse() {
		return mouse;
	}

	public void setMouse(Mouse mouse) {
		this.mouse = mouse;
	}

	public String getSeriemou() {
		return seriemou;
	}

	public void setSeriemou(String seriemou) {
		this.seriemou = seriemou;
	}

	public String getModelomou() {
		return modelomou;
	}

	public void setModelomou(String modelomou) {
		this.modelomou = modelomou;
	}

	public List<Mouse> getListaMouses() {
		return listaMouses;
	}

	public void setlistaMouses(List<Mouse> listaMouses) {
		this.listaMouses = listaMouses;
	}

	public String getActualfab4() {
		return actualfab4;
	}

	public void setActualfab4(String actualfab4) {
		this.actualfab4 = actualfab4;
	}

	public String getNuevofab4() {
		return nuevofab4;
	}

	public void setNuevofab4(String nuevofab4) {
		this.nuevofab4 = nuevofab4;
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
	
	public void resetMouse() {
		setSeriemou("");
		setModelomou("");
		setNuevofab4("");
	}

	public void altaMouse() throws Exception {
		try {
			Mouse mou = new Mouse();
			mou.setSerie(seriemou);
			mou.setModelo(modelomou);
			mou.setFabricante(equipoService.getFabByKey(nuevofab4));
			mouseService.altaMouse(mou);
			llenaMouse();
			resetMouse();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onCellEditMouse(CellEditEvent event) {
		
		System.out.println(actualfab4);
		
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		if (newValue != null && !newValue.equals(oldValue)) {
			DataTable p = (DataTable) event.getSource();
			Mouse mou = (Mouse) p.getRowData();
			try {
				Mouse mou1 = new Mouse();
				mou1.setId_mouse(mou.getId_mouse());
				mou1.setSerie(mou.getSerie());
				mou1.setModelo(mou.getModelo());
				mou1.setFabricante(mou.getFabricante());
				if (actualfab4 != null) {
					mou1.setFabricante(equipoService.getFabByKey(actualfab4));
				} else {
					mou1.setFabricante(mou.getFabricante());
				}
				
				mouseService.modificarMouse(mou1);
				
				actualfab4 = (null);
				
				llenaMouse();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void borrarMouse() {
		Mouse mou = selectedMou;
		try {
			Mouse mou1 = new Mouse();
			mou1.setId_mouse(mou.getId_mouse());
			mouseService.borrarMouse(mou);
			llenaMouse();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void llenaMouse() throws Exception {
		try {
			listaMouses = mouseService.getMouse();
			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"mouse?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setlistaMouses(listaMouses);

	}

	public Mouse getSelectedMou() {
		return selectedMou;
	}

	public void setSelectedMou(Mouse selectedMou) {
		this.selectedMou = selectedMou;
	}
	
private String fileName;
	
	public String getFileName() {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmss");
        fileName = "Reporte-Mouses".concat(formatter.format(new Date()));

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

		final Phrase phrase = new Phrase("Reporte Mouses");
		pdf.add(Image.getInstance(logo));
		pdf.add(phrase);
	}

	public void postProcessPDF(Object document) throws IOException,
			DocumentException {
		final Document pdf = (Document) document;
		pdf.setPageSize(PageSize.A4.rotate());

	}
	
	////////Monitor

	private Monitor mon;
	private String seriemon;
	private String modelomon;
	private List<Monitor> listaMonitors;
	private Monitor selectedMon;

	private String actualfab5;
	private String nuevofab5;
	
	public Monitor getMon() {
		return mon;
	}

	public void setMon(Monitor mon) {
		this.mon = mon;
	}

	public String getSeriemon() {
		return seriemon;
	}

	public void setSeriemon(String seriemon) {
		this.seriemon = seriemon;
	}

	public String getModelomon() {
		return modelomon;
	}

	public void setModelomon(String modelomon) {
		this.modelomon = modelomon;
	}

	public List<Monitor> getListaMonitors() {
		return listaMonitors;
	}

	public void setListaMonitors(List<Monitor> listaMonitors) {
		this.listaMonitors = listaMonitors;
	}

	public Monitor getSelectedMon() {
		return selectedMon;
	}

	public void setSelectedMon(Monitor selectedMon) {
		this.selectedMon = selectedMon;
	}

	public String getActualfab5() {
		return actualfab5;
	}

	public void setActualfab5(String actualfab5) {
		this.actualfab5 = actualfab5;
	}

	public String getNuevofab5() {
		return nuevofab5;
	}

	public void setNuevofab5(String nuevofab5) {
		this.nuevofab5 = nuevofab5;
	}
	
	public void resetMon() {
		setSeriemon("");
		setModelomon("");
		setNuevofab5("");
	}

	public void altaMon() throws Exception {
		System.out.println(seriemon);
		System.out.println(modelomon);
		System.out.println(nuevofab5);


		try {
			Monitor mon = new Monitor();
			mon.setSerie(seriemon);
			mon.setModelo(modelomon);
			mon.setFabricante(equipoService.getFabByKey(nuevofab5));
			mouseService.altaMon(mon);
			llenaMon();
			resetMon();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onCellEditMon(CellEditEvent event) {
		
		System.out.println(actualfab5);
		
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		if (newValue != null && !newValue.equals(oldValue)) {
			DataTable p = (DataTable) event.getSource();
			Monitor mon = (Monitor) p.getRowData();
			try {
				Monitor mon1 = new Monitor();
				mon1.setId_monitor(mon.getId_monitor());
				mon1.setSerie(mon.getSerie());
				mon1.setModelo(mon.getModelo());
				mon1.setFabricante(mon.getFabricante());
				if (actualfab5 != null) {
					mon1.setFabricante(equipoService.getFabByKey(actualfab5));
				} else {
					mon1.setFabricante(mon.getFabricante());
				}
				
				mouseService.modificarMon(mon1);
				
				actualfab5 = (null);
				
				llenaMon();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void borrarMon() {
		Monitor mon = selectedMon;
		try {
			Monitor mon1 = new Monitor();
			mon1.setId_monitor(mon.getId_monitor());
			mouseService.borrarMon(mon);
			llenaMon();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void llenaMon() throws Exception {
		try {
			listaMonitors = mouseService.getMonitor();
			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication()
					.getNavigationHandler();
			navigationHandler.handleNavigation(context, null,
					"monitor?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setListaMonitors(listaMonitors);

	}
	
private String fileName1;
	
	public String getFileName1() {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmss");
        fileName1 = "Reporte-Monitores".concat(formatter.format(new Date()));

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

		final Phrase phrase = new Phrase("Reporte Monitores");
		pdf.add(Image.getInstance(logo));
		pdf.add(phrase);
	}

	public void postProcessPDF1(Object document) throws IOException,
			DocumentException {
		final Document pdf = (Document) document;
		pdf.setPageSize(PageSize.A4.rotate());

	}

}
