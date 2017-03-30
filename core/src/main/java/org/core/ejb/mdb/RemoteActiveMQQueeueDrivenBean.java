package org.core.ejb.mdb;

import javax.annotation.PostConstruct;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * The Class RemoteActiveMQQueeueDrivenBean.
 */
/**
 * @author mabourouh
 *
 */

// @MessageDriven(mappedName = "mdb", activationConfig = {
// @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue =
// "Auto-acknowledge"),
// @ActivationConfigProperty(propertyName = "destinationType", propertyValue =
// "javax.jms.Queue"),
// @ActivationConfigProperty( propertyName="destination",
// propertyValue="busIntegration")
// }, messageListenerInterface = MessageListener.class)
public class RemoteActiveMQQueeueDrivenBean implements MessageListener {

	/* (non-Javadoc)
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	
	@PostConstruct
	public void init(){
		System.out.println(this);
	}
	
	
	@Override
	public void onMessage(Message arg0) {

		System.out.println(arg0);

	}

}
