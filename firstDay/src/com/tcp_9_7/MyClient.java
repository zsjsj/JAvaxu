package tcp_9_7;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Administrator on 2018/9/7.
 */
public class MyClient {
	public static void main(String[] args) throws Exception {
		Socket s = new Socket("127.0.0.1", 8888);
		OutputStream out = s.getOutputStream();
		int i = 0 ;
		while(true){
			out.write(("hello " + i + "\r\n").getBytes());
			out.flush();
			i ++ ;
			Thread.sleep(1000);
		}
	}
}
