package com.oldboy.spring.aop.service;

import com.oldboy.spring.aop.service.WelcomeService;

/**
 * Created by Administrator on 2018/9/17.
 */
public class WelcomeServiceImpl implements WelcomeService {
	public void sayHello(String s) {
		s.toLowerCase();
		System.out.println( "hello : " + s);
	}
}
