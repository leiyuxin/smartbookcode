package org.leiyuxin.chapter4.demo.aop;

import java.lang.reflect.Method;

import org.leiyuxin.chapter4.demo.CGLibProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLibDynamicProxy implements MethodInterceptor {
    private	static final Logger logger = LoggerFactory.getLogger(JDKDynamicProxy.class);
	private static CGLibDynamicProxy instance = new CGLibDynamicProxy();
	Object realObject ;
	private CGLibDynamicProxy() {

	}

	public static CGLibDynamicProxy getInstance() {
		return instance;

	}

	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<T> cls) {

		//现在要代理的对象交给Enhance来帮你建了
		Object realObject =  Enhancer.create(cls, this);
		this.realObject = realObject;
		return (T)realObject;
	}


	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		befor();
		Object result = proxy.invokeSuper(obj, args);
		System.out.println(obj == realObject);
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
