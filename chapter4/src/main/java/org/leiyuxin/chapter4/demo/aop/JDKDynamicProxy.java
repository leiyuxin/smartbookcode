package org.leiyuxin.chapter4.demo.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDKDynamicProxy implements InvocationHandler {
	static final Logger logger = LoggerFactory.getLogger(JDKDynamicProxy.class);
	private Object target;

	public JDKDynamicProxy(Object target) {
		this.target = target;
	}

	@SuppressWarnings("unchecked")
	public <T> T getProxy() {
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		befor();
		Object result = method.invoke(target, args);
		System.out.println(proxy == target);
		after();
		return result;
	}

	private void after() {
		logger.info("after{}", "proxyMethod");
	}

	private void befor() {
		logger.info("before{}", "proxyMethod");

	}

}
