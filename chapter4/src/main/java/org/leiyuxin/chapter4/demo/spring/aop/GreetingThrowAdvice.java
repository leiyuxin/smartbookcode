package org.leiyuxin.chapter4.demo.spring.aop;

import java.lang.reflect.Method;

import org.leiyuxin.chapter4.demo.AOPBeforAfterInfoUtil;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

@Component
public class GreetingThrowAdvice implements ThrowsAdvice {
	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
		AOPBeforAfterInfoUtil.info("thows exception");
		AOPBeforAfterInfoUtil.info("Target class" + target.getClass().getName());
		AOPBeforAfterInfoUtil.info("Method name:" + method.getName());
		AOPBeforAfterInfoUtil.info("Exception Message" + ex.getMessage());
		AOPBeforAfterInfoUtil.info("------------------------------------");
	}

}
