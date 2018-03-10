package org.leiyuxin.chapter4.demo.aop;

import org.springframework.stereotype.Component;

@Component
public class GreetingImpl implements Greeting {

	@Override
	public void sayHello(String name) {

		System.out.println("hello!" + name);
		/*throw new RuntimeException("Error");*/
	}

}
