package com_9_6.app;

import java.util.LinkedList;
import java.util.List;

/**
 * 容器
 */
public class Jar {

	public static int cam = 0 ;
	Object obj = new Object();
	Object obj1 = new Object();

	public  synchronized void add() throws Exception {

			while(cam ==50){
			this.wait();
			}
			cam++;
		System.out.println(Thread.currentThread().getName() + " + : " + "生产后蜂蜜数"+Jar.cam);
			this.notifyAll();
	}

	public  synchronized Integer removeall() throws Exception {
			while(cam < 20){
				this.wait();
			}
			cam = 0;
		System.out.println(Thread.currentThread().getName() + "- : " +"吃光了");
			this.notifyAll();
			return cam ;

	}
}

