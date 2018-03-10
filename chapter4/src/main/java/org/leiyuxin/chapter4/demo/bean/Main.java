package org.leiyuxin.chapter4.demo.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

		Employee employee = ctx.getBean(Employee.class);
		System.out.println(employee);

		Department department = ctx.getBean(Department.class);
		System.out.println(department);

		Operations operations = ctx.getBean(Operations.class);
		operations.helloWorld();
	}
}