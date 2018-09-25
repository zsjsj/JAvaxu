package com.oldboy.spring.aop2;

import com.oldboy.spring.aop2.Actor;

/**
 * 目标类
 */
public class Singer implements Actor {
	public void show() {
		System.out.println("~~~~啦啦啦");
		String s = null ;
		s.toLowerCase();
	}
}
