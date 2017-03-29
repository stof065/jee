package org.integration.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * The Interface BusInteracionFacade.
 *
 * @author mabourouh
 */
public class BusInteracionFacadeImpl implements BusInteracionFacade {

	/**
	 * Send message.
	 */

	@Autowired
	JmsTemplate jmsTemplate;

	public void sendMessage(Object o) {
		jmsTemplate.convertAndSend(o);
	}


}
