package org.integration.jms.endpoint;

import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;

public class StartProcessor {

	@ServiceActivator(inputChannel = "redirectChannel",poller=@Poller(fixedRate="500"))
	public void startChannel(Object o) {
		System.out.println("OK : " + o);
	}

}
