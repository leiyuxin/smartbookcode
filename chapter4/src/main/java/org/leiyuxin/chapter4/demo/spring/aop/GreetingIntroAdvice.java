package org.leiyuxin.chapter4.demo.spring.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.leiyuxin.chapter4.demo.AOPBeforAfterInfoUtil;
import org.leiyuxin.chapter4.demo.aop.Apology;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor implements Apology{
	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		return super.invoke(mi);
	}

	@Override
	public void saySorry(String name) {
		AOPBeforAfterInfoUtil.info(name);
	}


}
