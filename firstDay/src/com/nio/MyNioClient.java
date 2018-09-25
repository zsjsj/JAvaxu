package nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * 客户端
 */
public class MyNioClient {
	public static void main(String[] args) throws Exception {
		//
		SocketChannel sc = SocketChannel.open();
		//
		sc.configureBlocking(false) ;

		//连接到远程服务器
		InetSocketAddress srvAddr = new InetSocketAddress("localhost" , 8888) ;
		sc.connect(srvAddr) ;

		//挑选器
		Selector sel = Selector.open();

		//注册
		SelectionKey key = sc.register(sel , SelectionKey.OP_WRITE | SelectionKey.OP_CONNECT | SelectionKey.OP_READ) ;

		int index = 1 ;
		while(true){
			sel.select() ;
			if(key.isConnectable()){
				System.out.println("可连接!");
				sc.finishConnect();
			}
			if(key.isReadable()){
				SocketChannel sc0 = (SocketChannel) key.channel();
				System.out.println(getRemoteAddr(sc0) + " 可读了");
				String msg = readStringFromChannel(sc0) ;
				System.out.println(getRemoteAddr(sc0) + " 发来消息: " + msg);
			}

			if(key.isWritable()){
				System.out.println("可写");
				String msg = "tom" + index ;
				ByteBuffer buf = ByteBuffer.wrap(msg.getBytes()) ;
				//发送消息给服务器
				sc.write(buf) ;
				index ++ ;
			}
			//清空挑选集合
			sel.selectedKeys().clear();
			Thread.sleep(1000);
		}
	}
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

	/**
	 * 得到socket远程地址
	 */
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
