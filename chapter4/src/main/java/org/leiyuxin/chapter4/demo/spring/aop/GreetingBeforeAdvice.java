package org.leiyuxin.chapter4.demo.spring.aop;

import java.lang.reflect.Method;

import org.leiyuxin.chapter4.demo.AOPBeforAfterInfoUtil;
import org.springframework.aop.MethodBeforeAdvice;

public class GreetingBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		AOPBeforAfterInfoUtil.before("Advice");
	}

}
