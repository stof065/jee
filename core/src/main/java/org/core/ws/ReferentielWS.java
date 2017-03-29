package org.core.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.core.ejb.UserDao;
import org.core.model.EntityTest;

@Stateless
@WebService
public class ReferentielWS {

	@EJB
	UserDao userDao;

	public List<EntityTest> findAll() {
		return userDao.findAll();
	}

}
