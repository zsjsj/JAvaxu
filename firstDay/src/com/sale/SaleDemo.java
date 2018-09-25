package sale;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2018/9/9.
 */
public class SaleDemo {

	public static void main(String[] args) throws Exception {
		Object obj = new Object();
		ArrayList<Sales> list = new ArrayList<>();
		long start = System.currentTimeMillis();
		for (int i = 0;i<10;i++){
			list.add(new Sales("s"+i,obj));
		}
		for (Sales sales : list) {
			sales.start();
		}
		for (Sales sales : list) {
			sales.join();
		}
		long stop = System.currentTimeMillis();
		System.out.println(stop-start);
    }




	/**
	 * 售票员
	 */
	static class Sales extends Thread{
		private  Object obj ;
		private int tickets = 1000;
		private String ssname ;
		private Sales(String ssname,Object obj){
			this.ssname = ssname ;
			this.obj=obj;
		}

		public void run() {
				synchronized (obj){
					while (true){
					if(tickets>0){
						System.out.println(Thread.currentThread().getName()+":卖出第" +tickets+"张票");
						tickets--;
					}else {
						break;
					}
				}
			}
		}

		}


}
