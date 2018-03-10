package org.leiyuxin.chapter4.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloImpl implements Hello {
	Logger logger = LoggerFactory.getLogger(HelloImpl.class);

	@Override
	public void say(String name) {
		logger.info("name is {}", name);

	}

}
