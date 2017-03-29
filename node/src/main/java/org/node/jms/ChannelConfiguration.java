package org.node.jms;

import org.integration.jms.JmsConfiguration;
import org.node.jms.workflow.FlowMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.AbstractPollableChannel;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;

/**
 * The Class ChannelConfiguration.
 *
 * @author mabourouh
 */
@Configuration
@EnableJms
public class ChannelConfiguration extends JmsConfiguration {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.integration.jms.JmsConfiguration#redirectChannel()
	 */

	@Bean
	public MessageChannel redirectChannel() {
		return super.redirectChannel();
	}

	@Bean
	FlowMessage flowMessage() {
		return new FlowMessage();
	}

//	@Bean
//	public MessageChannel testoutputchannel() {
//		return new AbstractPollableChannel() {
//
//			@Override
//			protected boolean doSend(Message<?> message, long timeout) {
//				return false;
//			}
//
//			@Override
//			protected Message<?> doReceive(long timeout) {
//				return null;
//			}
//		};
//	}

}
