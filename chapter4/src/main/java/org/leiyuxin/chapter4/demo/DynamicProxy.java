package org.leiyuxin.chapter4.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;


public class DynamicProxy implements InvocationHandler {
	private Logger logger = LoggerFactory.getLogger(DynamicProxy.class);
	private Marker maker = MarkerFactory.getMarker("BasicMark");
	private Object target;

	public DynamicProxy(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object result = method.invoke(target, args);
		after();
		return result;
	}

	private void after() {
		logger.debug(maker, "to do after{}","DynamicProxy");

	}

	private void before() {
		logger.debug(maker, "to do before{}","DynamicProxy");
	}

	 @SuppressWarnings("unchecked")
	public <T> T getProxy() {
		 return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	 }
	public static void main(String[] args) {
		HelloImpl hello = new HelloImpl();
		DynamicProxy dynamicProxy = new DynamicProxy(hello);
		//Hello helloProxy = (Hello) Proxy.newProxyInstance(hello.getClass().getClassLoader(), hello.getClass().getInterfaces(), dynamicProxy);
		Hello helloProxy = dynamicProxy.getProxy();
		helloProxy.say("jack");
	}
}
