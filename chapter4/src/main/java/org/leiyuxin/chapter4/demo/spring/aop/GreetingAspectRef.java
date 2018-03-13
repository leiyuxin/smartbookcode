package org.leiyuxin.chapter4.demo.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.leiyuxin.chapter4.demo.aop.Apology;
import org.leiyuxin.chapter4.demo.aop.ApologyIml;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class GreetingAspectRef {
	//value 是目标类,defaultImpl
	@DeclareParents(value="org.leiyuxin.chapter4.demo.aop.GreetingImpl",defaultImpl=ApologyIml.class)
	private Apology apology;
}
