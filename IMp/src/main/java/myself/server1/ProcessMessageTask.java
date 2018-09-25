package myself.server1;


import myself.common1.BaseMessage;
import myself.common1.ServerChatMessage;
import myself.common1.ServerClientFile;
import myself.util1.MessageFactory;
import util.QQUtil;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 处理消息
 */
public class ProcessMessageTask implements  Runnable{
	private QqServer qqServer;

	private SelectionKey key  ; ;

	public ProcessMessageTask(SelectionKey key ,QqServer qqServer){
		this.key = key;
		this.qqServer = qqServer;
	}
	public void run() {
		QqServer server = qqServer.getInstance();
		SocketChannel sc = (SocketChannel) key.channel();
		ReentrantLock lock = (ReentrantLock) key.attachment();
		boolean b = lock.tryLock() ;
		if(b){
			try {
				BaseMessage msg = MessageFactory.parsemessagefromchannel(sc) ;
				if(msg != null){
					switch (msg.getMessageType()){
						case BaseMessage.SERVER_TO_CLINT_CHATS:
							server.broadmessage(msg);
							break ;
						case BaseMessage.SERVER_TO_CLINT_CHAT:
							byte[] recvAddrBytes = ((ServerChatMessage) msg).getRecvAddrBytes();
							server.sendMessage(msg , new String(recvAddrBytes));
							break ;
						case BaseMessage.SERVER_TO_CLINT_FRIENDS:
							server.sendMessage(msg , QQUtil.getRemoteAddr(sc.socket()));
							break ;
						case BaseMessage.SERVER_TO_CLINT_FiLE:
							server.sendFile((ServerClientFile) msg);

					}
				}
			} catch (Exception e) {
				try {
					e.getStackTrace();
					System.out.println("有人下线更新好友列表");
					qqServer.broadmessage(qqServer.genFriendListMessage());
					key.cancel();
				} catch (Exception e1) {
					System.out.println("有异常");
					e1.printStackTrace();
				}
			}
			lock.unlock();
		}
	}
}
