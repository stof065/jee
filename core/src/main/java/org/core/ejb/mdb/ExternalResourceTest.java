package org.core.ejb.mdb;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

@Singleton
@Startup
public class ExternalResourceTest {

	@Resource(lookup = "java:/AMQConnectionFactory")
	ConnectionFactory cnf;

	@Resource(lookup = "java:/queue/integration.busQueue")
	Destination destination;

	@PostConstruct
	public void init() {
		System.out.println(this);

		sendMessage();
	}

	private void sendMessage() {

		try {
			Connection cnx = cnf.createConnection();
			Session createSession = cnx.createSession(false, 0);
			MessageProducer producer = createSession.createProducer(destination);
			producer.send(createSession.createTextMessage("hello"));
			cnx.close();

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
