package org.kivio.server.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class LoggerProducer {
	private static final Logger LOG = LoggerFactory.getLogger(LoggerProducer.class);
	
	@Produces
	public Logger createLogger(InjectionPoint injectionPoint) {
		Class<?> parentClass = injectionPoint.getBean().getClass();
		
		LOG.debug("Producing Logger for class {}", parentClass);
		
		return LoggerFactory.getLogger(parentClass);
	}
}
