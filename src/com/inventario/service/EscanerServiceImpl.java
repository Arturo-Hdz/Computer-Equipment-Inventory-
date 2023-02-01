package com.inventario.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.dao.EscanerDao;
import com.inventario.dao.ImpresoraDao;
import com.inventario.model.Scanner;
import com.inventario.model.Nobreak;



@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class EscanerServiceImpl implements Serializable, EscanerService {

	private static final long serialVersionUID = -5579260433520427408L;
	@Autowired
	 EscanerDao escanerDao;

	 @Override
	    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	    public void altaEsca(Scanner esca) throws Exception {
		escanerDao.altaEsca(esca);
	    }
	    
	    @Override
	    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	    public void modificarEsca(Scanner esca1) throws Exception {
		escanerDao.modificarEsca(esca1);
	    }
	    
	    @Override
	    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	    public void borrarEsca(Scanner esca) throws Exception {
		escanerDao.borrarEsca(esca);
	    }
	    
	    @Override
	    public List<Scanner> getEscaner() throws Exception {
		return escanerDao.getEscaner();
	    }
	    
	    ////////Nobreak
	    
	    @Override
	    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	    public void altaNob(Nobreak nob) throws Exception {
		escanerDao.altaNob(nob);
	    }
	    
	    @Override
	    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	    public void modificarNob(Nobreak nob1) throws Exception {
		escanerDao.modificarNob(nob1);
	    }
	    
	    @Override
	    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	    public void borrarNob(Nobreak nob) throws Exception {
		escanerDao.borrarNob(nob);
	    }
	    
	    @Override
	    public List<Nobreak> getNobreak() throws Exception {
		return escanerDao.getNobreak();
	    }
	   
}