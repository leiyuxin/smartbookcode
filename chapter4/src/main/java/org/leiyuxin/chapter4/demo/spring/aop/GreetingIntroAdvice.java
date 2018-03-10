package org.leiyuxin.chapter4.demo.spring.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.leiyuxin.chapter4.demo.AOPBeforAfterInfoUtil;
import org.leiyuxin.chapter4.demo.aop.Apology;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;
@Component
public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor implements Apology{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		return super.invoke(mi);
	}

	@Override
	public void saySorry(String name) {
		AOPBeforAfterInfoUtil.info("Sorry!"+name);
	}


}
