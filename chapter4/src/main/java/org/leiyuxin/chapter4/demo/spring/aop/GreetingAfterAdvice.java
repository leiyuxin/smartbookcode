package org.leiyuxin.chapter4.demo.spring.aop;

import java.lang.reflect.Method;

import org.leiyuxin.chapter4.demo.AOPBeforAfterInfoUtil;
import org.springframework.aop.AfterReturningAdvice;

public class GreetingAfterAdvice implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		AOPBeforAfterInfoUtil.after("Advice");
	}

}
