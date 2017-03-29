package org.core.ejb.mdb;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.ConnectionFactory;

@Singleton
@Startup
public class ExternalResourceTest {

	@Resource(lookup="java:/amq/ConnectionFactory")
	ConnectionFactory cnf;

	@PostConstruct
	public void init() {
		System.out.println(this);
	}
	
	

}
