package com.oldboy.spring.aop.service;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 环绕通知
 */
public class MyMethodInterceptor implements MethodInterceptor {
	public Object invoke(MethodInvocation invocation) throws Throwable {
		long start = System.nanoTime();
		//调用目标对象的方法
		Object obj = invocation.proceed();
		System.out.println(System.nanoTime()-start);
		return obj;
	}
}
