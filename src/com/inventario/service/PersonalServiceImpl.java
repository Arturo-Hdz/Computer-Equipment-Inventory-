package com.inventario.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.dao.PersonalDao;
import com.inventario.model.Personal;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class PersonalServiceImpl implements Serializable, PersonalService {

	private static final long serialVersionUID = 3048346419496718272L;

	@Autowired
	PersonalDao personalDao;

	// ///Personal

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void altaPersonal(Personal person) throws Exception {
		personalDao.altaPersonal(person);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void modificarPersonal(Personal person1) throws Exception {
		personalDao.modificarPersonal(person1);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void borrarPersonal(Personal person) throws Exception {
		personalDao.borrarPersonal(person);
	}

	@Override
	public List<Personal> getPersonal() throws Exception {
		return personalDao.getPersonal();
	}

}