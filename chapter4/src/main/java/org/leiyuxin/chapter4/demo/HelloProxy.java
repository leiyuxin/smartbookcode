package org.leiyuxin.chapter4.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloProxy extends HelloImpl {
	private Hello hello;
	private static final  Logger loggerproxy = LoggerFactory.getLogger(HelloProxy.class);

	public HelloProxy() {

		hello = new HelloImpl();
	}

	@Override
	public void say(String name) {
		before();
		hello.say(name);
		after();
	}

	private void after() {
		loggerproxy.info("after to do check{}", "name");
	}

	private void before() {
		loggerproxy.info("before to do check{}", "name");
	}
	public static void main(String[] args) {
		Hello helloProxy = new HelloProxy();
		helloProxy.say("jack");
	}
}
