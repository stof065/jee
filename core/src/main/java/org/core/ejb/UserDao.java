package org.core.ejb;

import javax.ejb.Startup;
import javax.ejb.Stateful;
import javax.inject.Named;

import org.core.ejb.commun.AbstractDao;
import org.core.model.EntityTest;

/**
 * The Class UserDao.
 */
@Stateful
@Startup
public class UserDao extends AbstractDao<EntityTest, Long> {

	

}
