package com.inventario.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.dao.MouseDao;
import com.inventario.model.Monitor;
import com.inventario.model.Mouse;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class MouseServiceImpl implements Serializable, MouseService {

	private static final long serialVersionUID = -4780865465984071418L;
	@Autowired
	MouseDao mouseDao;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void altaMouse(Mouse mouse) throws Exception {
		mouseDao.altaMouse(mouse);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void modificarMouse(Mouse mou1) throws Exception {
		mouseDao.modificarMouse(mou1);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void borrarMouse(Mouse mou) throws Exception {
		mouseDao.borrarMouse(mou);
	}

	@Override
	public List<Mouse> getMouse() throws Exception {
		return mouseDao.getMouse();
	}

	// //////Monitor

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void altaMon(Monitor mon) throws Exception {
		mouseDao.altaMon(mon);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void modificarMon(Monitor mon1) throws Exception {
		mouseDao.modificarMon(mon1);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void borrarMon(Monitor mon) throws Exception {
		mouseDao.borrarMon(mon);
	}

	@Override
	public List<Monitor> getMonitor() throws Exception {
		return mouseDao.getMonitor();
	}

}