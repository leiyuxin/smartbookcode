package org.leiyuxin.chapter4.demo.spring.aop;

import org.leiyuxin.chapter4.demo.aop.Greeting;
import org.leiyuxin.chapter4.demo.aop.GreetingImpl;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
	public static void main(String[] args) {
	/**
	 * 方法一
	 */
	//	ProxyFactory proxyFactory = new ProxyFactory(); //创建代理工厂

	//	proxyFactory.setTarget(new GreetingImpl()); //摄入目标类对象
	//	proxyFactory.addAdvice(new GreetingAfterAdvice());//添加前置增强
	//	proxyFactory.addAdvice(new GreetingBeforeAdvice());//添加后置增强
	//	proxyFactory.addAdvice(new GreetingBeforeAndAfterAdvice());
	//	proxyFactory.addAdvice(new GreetingAroundAdvice());
	//	Greeting greeting = (Greeting) proxyFactory.getProxy();//从代理工厂中获取代理
	//	greeting.sayHello("JacKSPringAop");//调用代理的方法

	/*
	 * 方法二
	 */
		//使用配置文件applicationContext.xml 使用Spring IOC 生成对象以及调用代理方法
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");//获取SpringContext 也就是IOC容器
		Greeting greeting = (Greeting) context.getBean("greetingProxy");
		greeting.sayHello("jack");//调用代理方法
	}
}
