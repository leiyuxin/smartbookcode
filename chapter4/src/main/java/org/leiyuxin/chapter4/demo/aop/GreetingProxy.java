package org.leiyuxin.chapter4.demo.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreetingProxy implements Greeting {
	Logger logger = LoggerFactory.getLogger(GreetingProxy.class);
	private GreetingImpl greetingImpl;
	public GreetingProxy(GreetingImpl greetingImpl) {
		this.greetingImpl = greetingImpl;
	}
	@Override
	public void sayHello(String name) {
		before();
		greetingImpl.sayHello(name);
		after();
	}
	private void after() {
		logger.info("after{}","sayHello Method");

	}
	private void before() {
		logger.info("before{}","sayHello Method");
	}

}
