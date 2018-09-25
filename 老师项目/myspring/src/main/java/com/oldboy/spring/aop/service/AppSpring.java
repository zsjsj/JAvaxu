package com.oldboy.spring.aop.service;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2018/9/17.
 */
public class AppSpring {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("aop.xml") ;
	WelcomeService w = (WelcomeService)ac.getBean("welcomeService");
		w.sayHello("ton");

	}
}
