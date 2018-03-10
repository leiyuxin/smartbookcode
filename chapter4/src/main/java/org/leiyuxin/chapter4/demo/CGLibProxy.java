package org.leiyuxin.chapter4.demo;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLibProxy implements MethodInterceptor {
	private static CGLibProxy instance = new CGLibProxy();
	private Logger logger = LoggerFactory.getLogger(DynamicProxy.class);
	private Marker maker = MarkerFactory.getMarker("BasicMark");

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		before();
		Object result = proxy.invokeSuper(obj, args);
		after();
		return result;
	}

	private void after() {
		logger.debug(maker, "to do after{}", "DynamicProxy");

	}

	private void before() {
		logger.debug(maker, "to do before{}", "DynamicProxy");
	}

	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<T> cls) {
		return (T) Enhancer.create(cls, this);
	}

	private CGLibProxy() {

	}

	public static CGLibProxy getInstance() {
		return instance;

	}

	public static void main(String[] args) {
		HelloImpl helloProxy = getInstance().getProxy(HelloImpl.class);
		helloProxy.say("jack");
	}
}
