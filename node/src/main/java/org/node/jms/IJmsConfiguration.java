package org.node.jms;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;

public interface IJmsConfiguration {
	
	@Bean MessageChannel redirectChannel() ;

}
