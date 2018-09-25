package com_9_6.app;

/**
 * 生产者
 */
public class Bee  extends Thread{
	public String pname ;
	public Jar jar;
	public Bee(String pname , Jar jar){
		this.pname = pname ;
		this.jar = jar;
	}

	public void run() {
		while(true){
			try {
				jar.add();
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}


		}
	}
}
