package org.leiyuxin.chapter4.demo.aop;

public class Client {
public static void main(String[] args) {
//	Greeting greetingProxy = new GreetingProxy(new GreetingImpl());
//	greetingProxy.sayHello("jack");

	Greeting greeting =new JDKDynamicProxy(new GreetingImpl()).getProxy();
	greeting.sayHello("JSON");

	greeting = CGLibDynamicProxy.getInstance().getProxy(GreetingImpl.class);
	greeting.sayHello("CGLibDynamicProxy");

}
}
