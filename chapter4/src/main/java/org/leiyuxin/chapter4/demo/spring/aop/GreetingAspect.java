package org.leiyuxin.chapter4.demo.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.leiyuxin.chapter4.demo.AOPBeforAfterInfoUtil;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class GreetingAspect {
	@Around("execution(* org.leiyuxin.chapter4.demo.aop.GreetingImpl.*(..))")
	Object around(ProceedingJoinPoint pjp) throws Throwable{
		before();
		Object result = pjp.proceed();
		after();
		return result;
	}

	private void after() {
		AOPBeforAfterInfoUtil.after("AspectJ");
	}

	private void before() {
		AOPBeforAfterInfoUtil.before("AspectJ");
	}
}
