package org.integration.jms;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.integration.facade.BusInteracionFacade;
import org.integration.facade.BusInteracionFacadeImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.integration.dsl.jms.JmsMessageDrivenChannelAdapter;
import org.springframework.integration.jms.ChannelPublishingJmsMessageListener;
import org.springframework.integration.jms.PollableJmsChannel;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.AbstractMessageListenerContainer;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.messaging.MessageChannel;

public class JmsConfiguration {

	@Bean
	@DependsOn("redirectChannel")
	public JmsMessageDrivenChannelAdapter adapter(AbstractMessageListenerContainer listenerContainer,
			ChannelPublishingJmsMessageListener listener) {
		JmsMessageDrivenChannelAdapter jmdc = new JmsMessageDrivenChannelAdapter(listenerContainer, listener);
		jmdc.setOutputChannel(redirectChannel());
		return jmdc;
	}

	protected MessageChannel redirectChannel() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory());
		try {
			jmsTemplate.setDefaultDestination(InitialContext.doLookup("redirectionQueue"));
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new PollableJmsChannel(jmsTemplate);
	}

	@Bean
	public AbstractMessageListenerContainer JmsListenerContainer() {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setDestination(destination());
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
		return simpleMessageListenerContainer;
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		try {
			InitialContext ctx = new InitialContext();
			ConnectionFactory cnf = (ConnectionFactory) ctx.lookup("ConnectionFactory");
			// work indepndenly from the broker impl
			// config for ActiveMQ
			if (cnf instanceof ActiveMQConnectionFactory) {
				((ActiveMQConnectionFactory) cnf).setTrustAllPackages(true);
			}
			return cnf;

		} catch (NamingException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Bean
	public Destination destination() {
		try {
			InitialContext ctx = new InitialContext();
			return (Destination) ctx.lookup("busQueue");
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Bean
	public ChannelPublishingJmsMessageListener channelPublishingJmsMessageListener() {
		ChannelPublishingJmsMessageListener channelPublishingJmsMessageListener = new ChannelPublishingJmsMessageListener();
		channelPublishingJmsMessageListener.setRequestChannel(channel());
		return channelPublishingJmsMessageListener;
	}

	@Bean
	public MessageChannel channel() {
		return new PollableJmsChannel(jmsTemplate());
	}

	@Bean
	@Primary
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setDefaultDestination(destination());
		return template;
	}

	@Bean
	public BusInteracionFacade BusInteracionFacade() {
		return new BusInteracionFacadeImpl();
	}
	
	

}
