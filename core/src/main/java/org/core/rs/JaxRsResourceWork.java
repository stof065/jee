package org.core.rs;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.core.ejb.UserDao;
import org.core.model.EntityTest;

/**
 * The Class HelloWorldResource.
 */
/**
 * @author mabourouh
 *
 */
@Path("/testpath")
public class JaxRsResourceWork {

	@EJB
	UserDao userdao;

	/**
	 * Gets the entities test.
	 *
	 * @return the entities test
	 */
	@GET
	@Path("/getAllEntitiesTest")
	@Produces("application/json")
	public List<EntityTest> getEntitiesTest() {
		return userdao.findAll();
	}
}