package org.integration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = BootApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JmsTest {

	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	ConnectionFactory cnf;

	@Autowired
	@Qualifier()
	Destination destination;

	@Test
	public void sendJms() {

		Connection createConnection;
		try {
			createConnection = cnf.createConnection();
			Session s = createConnection.createSession(false, 1);
			createConnection.start();
			MessageProducer msgP = s.createProducer(destination);
			TextMessage msg = s.createTextMessage("hello world !!");
			msg.setStringProperty("target", "OK");
			msgP.send(msg);

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
