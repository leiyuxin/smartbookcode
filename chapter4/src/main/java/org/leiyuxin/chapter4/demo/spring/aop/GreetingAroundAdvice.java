package org.leiyuxin.chapter4.demo.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.leiyuxin.chapter4.demo.AOPBeforAfterInfoUtil;
import org.springframework.stereotype.Component;

@Component
public class GreetingAroundAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		befor();
		Object result = invocation.proceed();
		AOPBeforAfterInfoUtil.info(invocation.getMethod().getName());
		after();
		return result;
	}

	private void after() {
		AOPBeforAfterInfoUtil.after("MethodInterceptor");
	}

	private void befor() {
		AOPBeforAfterInfoUtil.before("MethodInterceptor");

	}

}
