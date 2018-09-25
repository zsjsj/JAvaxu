package com_9_6.app;

/**
 * Created by Administrator on 2018/9/6.
 */
public class App {
	public static void main(String[] args) {
		Jar pool = new Jar();
		for (int i= 0;i<100;i++){
			Bee  p = new Bee("p"+i , pool) ;
			p.start();
		}

		Bear c = new Bear("c1" , pool) ;
		Bear c2 = new Bear("c2" , pool) ;

		c.start();
		c2.start();
	}
}
