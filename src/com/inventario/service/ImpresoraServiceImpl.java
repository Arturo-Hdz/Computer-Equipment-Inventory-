package com.inventario.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.dao.ImpresoraDao;
import com.inventario.model.Impresora;
import com.inventario.model.Teclado;



@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class ImpresoraServiceImpl implements Serializable, ImpresoraService {

	private static final long serialVersionUID = 6922085234052816177L;

	@Autowired
	 ImpresoraDao impresoraDao;

	 @Override
	    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	    public void altaImpre(Impresora impre) throws Exception {
		impresoraDao.altaImpre(impre);
	    }
	    
	    @Override
	    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	    public void modificarImpre(Impresora impre1) throws Exception {
		impresoraDao.modificarImpre(impre1);
	    }
	    
	    @Override
	    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	    public void borrarImpre(Impresora impre) throws Exception {
		impresoraDao.borrarImpre(impre);
	    }
	    
	    @Override
	    public List<Impresora> getImpresora() throws Exception {
		return impresoraDao.getImpresora();
	    }
	    
	    ////////Teclado
	    
	    @Override
	    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	    public void altaTecla(Teclado tecla) throws Exception {
		impresoraDao.altaTecla(tecla);
	    }
	    
	    @Override
	    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	    public void modificarTecla(Teclado tecla1) throws Exception {
		impresoraDao.modificarTecla(tecla1);
	    }
	    
	    @Override
	    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	    public void borrarTecla(Teclado tecla) throws Exception {
		impresoraDao.borrarTecla(tecla);
	    }
	    
	    @Override
	    public List<Teclado> getTeclado() throws Exception {
		return impresoraDao.getTeclado();
	    }
	   
}