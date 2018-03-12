package org.leiyuxin.chapter4.demo.aop;

import org.leiyuxin.chapter4.demo.AOPBeforAfterInfoUtil;
import org.leiyuxin.chapter4.demo.spring.aop.Tag;
import org.springframework.stereotype.Component;

@Component
public class GreetingImpl implements Greeting {

	@Override
	public void sayHello(String name) {
		AOPBeforAfterInfoUtil.info("hello!"+name);
		//throw new RuntimeException("Error");  异常拦截
	}
	@Tag
	public void goodMorning(String name) {
		AOPBeforAfterInfoUtil.info("Good Morning!" +name);
	}

	public void goodNight(String name) {
		AOPBeforAfterInfoUtil.info("Good Night!"+name);
	}
}
