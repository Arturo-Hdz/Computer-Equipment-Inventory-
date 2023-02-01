package com.inventario.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.dao.SoDao;
import com.inventario.model.Sistemaoperativo;



@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class SoServiceImpl implements Serializable, SoService {

	private static final long serialVersionUID = 6922085234052816177L;

	@Autowired
	 SoDao soDao;

	 @Override
	    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	    public void altaSo(Sistemaoperativo so) throws Exception {
		soDao.altaSo(so);
	    }
	    
	    @Override
	    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	    public void modificarSo(Sistemaoperativo so) throws Exception {
		soDao.modificarSo(so);
	    }
	    
	    @Override
	    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	    public void borrarSo(Sistemaoperativo so) throws Exception {
		soDao.borrarSo(so);
	    }
	    
	    @Override
	    public List<Sistemaoperativo> getSistemaoperativo() throws Exception {
		return soDao.getSistemaoperativo();
	    }
	   
}