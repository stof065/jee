package org.node;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = App.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JmsTest {

	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	ConnectionFactory cnf;

	@Autowired
	@Qualifier("destination")
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
			TextMessage msg1 = s.createTextMessage("hello world !!");
			msg.setStringProperty("target", "node");

			msg1.setStringProperty("target", "OK");
			msgP.send(msg1);

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
