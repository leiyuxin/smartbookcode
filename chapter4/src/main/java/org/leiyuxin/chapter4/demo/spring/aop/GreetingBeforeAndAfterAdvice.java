package org.leiyuxin.chapter4.demo.spring.aop;

import java.lang.reflect.Method;

import org.leiyuxin.chapter4.demo.AOPBeforAfterInfoUtil;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

public class GreetingBeforeAndAfterAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		AOPBeforAfterInfoUtil.after("ReturningAdivice");
	}

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		AOPBeforAfterInfoUtil.before("MethodAdvice");

	}

}
