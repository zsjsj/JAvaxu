package com_9_6.app;

/**
 * 生产者
 */
public class Bear extends Thread{
	public String cname;
	public Jar jar ;
	public Bear(String cname, Jar jar){
		this.cname = cname;
		this.jar = jar ;
	}

	public void run() {
		while(true){
			try {
			 jar.removeall();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}