package org.core.ejb.mdb;

import javax.annotation.PostConstruct;
import javax.jms.Message;
import javax.jms.MessageListener;


/**
 * The Class RemoteActiveMQQueeueDrivenBean.
 */
/**
 * @author mabourouh
 *
 */

// @ActivationConfigProperty(propertyName = "acknowledgeMode",
// propertyValue = "Auto-acknowledge"),
// @ActivationConfigProperty(propertyName = "jndiParameters", propertyValue =
// "java.naming.factory.initial=org.apache.activemq.jndi.ActiveMQInitialContextFactory;java.naming.provider.url=tcp://127.0.0.1:61616"),

// @MessageDriven(mappedName = "mdb", activationConfig = {
// @ActivationConfigProperty(propertyName = "destinationType", propertyValue =
// "javax.jms.Queue"),
// @ActivationConfigProperty(propertyName = "destination", propertyValue =
// "java:/queue/integration.busQueue") })
// @ResourceAdapter("activemq-ra")
public class RemoteActiveMQQueeueDrivenBean implements MessageListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */

	@PostConstruct
	public void init() {
		System.out.println(this);
	}

	@Override
	public void onMessage(Message arg0) {

		System.out.println(arg0);

	}

}
