package org.leiyuxin.chapter4.demo.spring.aop;

import org.leiyuxin.chapter4.demo.aop.Apology;
import org.leiyuxin.chapter4.demo.aop.Greeting;
import org.leiyuxin.chapter4.demo.aop.GreetingImpl;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
	public static void main(String[] args) {
		/**
		 * 方法一 ，对方法的增强
		 */
		// ProxyFactory proxyFactory = new ProxyFactory(); //创建代理工厂

		// proxyFactory.setTarget(new GreetingImpl()); //摄入目标类对象
		// proxyFactory.addAdvice(new GreetingAfterAdvice());//添加前置增强
		// proxyFactory.addAdvice(new GreetingBeforeAdvice());//添加后置增强
		// proxyFactory.addAdvice(new GreetingBeforeAndAfterAdvice());
		// proxyFactory.addAdvice(new GreetingAroundAdvice());
		// Greeting greeting = (Greeting) proxyFactory.getProxy();//从代理工厂中获取代理
		// greeting.sayHello("JacKSPringAop");//调用代理的方法

		/*
		 * 方法二， 对方法的增强
		 */
		// 使用配置文件applicationContext.xml 使用Spring IOC 生成对象以及调用代理方法，applicationContext.xml
		// 名字可以随便取
		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("applicationContext.xml");// 获取SpringContext
		// 也就是IOC容器
		// Greeting greeting = (Greeting) context.getBean("greetingProxy");
		// greeting.sayHello("jack");//调用代理方法

		// 引入增强 就是对类的增强
	/*	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");// 获取SpringContext //
																									// 也就是IOC容器
		GreetingImpl greeingImp = (GreetingImpl) context.getBean("greetingProxy");
		greeingImp.sayHello("jack");
		greeingImp.goodMorning("早上好");
		greeingImp.goodNight("晚上好");
		Apology apology = (Apology) greeingImp;
		apology.saySorry("Jack");
*/
		//使用AspectJ+spring
		ApplicationContext contextAspectJ = new ClassPathXmlApplicationContext("AspectJ.xml");

		GreetingImpl greeingImpS = (GreetingImpl) contextAspectJ.getBean(GreetingImpl.class);
		greeingImpS.sayHello("jack");
		greeingImpS.goodMorning("早上好");
		greeingImpS.goodNight("晚上好");
	}
}
