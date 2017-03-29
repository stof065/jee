package org.node.jms.workflow;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.node.repo.EntityTestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.Header;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class FlowMessage {

	@Value("${spring.app.name}")
	String appName;

	@Autowired
	EntityTestRepo entityRepo;

	@Autowired
	JmsTemplate JmsTemplate;

	@Router(inputChannel = "redirectChannel",  poller = @Poller(fixedRate = "500"))
	public String nodeSpliter(@Header("target") String target, Object o) {

		if (appName.equalsIgnoreCase(target)) {
			return "testoutputchannel";
		} else {
			JmsTemplate.send(new MessageCreator() {
				
				@Override
				public Message createMessage(Session session) throws JMSException {
					session.createTextMessage("hello world !!");
					TextMessage msg = session.createTextMessage("hello world !!");
					msg.setStringProperty("target", target);
					return msg;
				}
			});
			return null;

		}
	}

	@ServiceActivator(inputChannel = "testoutputchannel")
	public void action(Object o) {
		System.out.println("action : " + entityRepo.findAll() + "\n" + o);
	}

}
