package tcp_9_7;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2018/9/9.
 */
public class MyNioServer {
	public static void main(String[] args) throws Exception {
		//开启服务器套接字通道
		ServerSocketChannel ssc = ServerSocketChannel.open();

		InetSocketAddress addr = new InetSocketAddress("0.0.0.0" , 8888) ;
		//绑定地址
		ssc.bind(addr) ;

		//配置非阻塞
		ssc.configureBlocking(false) ;

		//开启挑选器
		Selector sel = Selector.open();

		//在挑选器中注册服务器通道
		ssc.register(sel , SelectionKey.OP_ACCEPT) ;

		for(;;){
			//开始挑选
			System.out.println("开始挑选..");
			sel.select() ;
			//得到挑选出来的集合
			Set<SelectionKey> keys = sel.selectedKeys();
			Iterator<SelectionKey> it = keys.iterator();
			while(it.hasNext()){
				SelectionKey key = it.next();

				//是否是可接受的
				if(key.isAcceptable()){
					//接受连接
					SocketChannel sc = ssc.accept();
					System.out.println("接受了新连接!");
					sc.configureBlocking(false) ;
					sc.register(sel , SelectionKey.OP_READ | SelectionKey.OP_CONNECT | SelectionKey.OP_WRITE) ;
				}

				//是否可读
				if(key.isReadable()){
					SocketChannel sc0 = (SocketChannel) key.channel();
					System.out.println(getRemoteAddr(sc0) + " 可读了");
					String msg = readStringFromChannel(sc0) ;
					System.out.println(getRemoteAddr(sc0) + " 发来消息: " + msg);
				}

				if(key.isConnectable()){
					SocketChannel sc0 = (SocketChannel) key.channel();
					System.out.println(getRemoteAddr(sc0) + " 可连接");
					sc0.finishConnect();
				}
			}
		}
	}

	/**
	 * 从channel中读取string
	 */
	public static String readStringFromChannel(SocketChannel sc) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream() ;
		ByteBuffer buf = ByteBuffer.allocate(1024) ;
		while(sc.read(buf) != 0){
			buf.flip() ;
			baos.write(buf.array() , 0 , buf.limit());
			buf.clear();
		}
		return new String(baos.toByteArray()) ;
	}

	public static String getRemoteAddr(SocketChannel sc) {
		try {
			InetSocketAddress addr = (InetSocketAddress) sc.getRemoteAddress();
			String ip = addr.getAddress().getHostAddress();
			int port = addr.getPort() ;
			return ip + ":" + port ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null ;
	}
}
