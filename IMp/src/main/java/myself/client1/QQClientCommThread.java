package myself.client1;







import myself.common1.*;
import myself.util1.Constant;
import myself.util1.DataUtil;
import myself.util1.MessageFactory;


import java.io.*;

import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * client通信线程
 */
public class QQClientCommThread extends Thread {
	//
	public QQClientChatsUI ui ;
	public Socket s;
	public QQClientCommThread(QQClientChatsUI ui){
		try {
			this.ui = ui;
			s = new Socket(Constant.QQ_CLIENT_SERVER_IP,Constant.QQ_CLIENT_SERVER_PORT) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {

			for(;;){
				BaseMessage msg = MessageFactory.parseServerMesageFromSocket(s);
				if(msg != null){
					System.out.println("收到服务器消息");
					switch (msg.getMessageType()){
						//群聊
						case BaseMessage.CLINT_TO_SERVER_CHATS:
						{
							ClientChatsMessage msg0 = (ClientChatsMessage) msg;
							//更新历史区
							ui.updateHistory(msg0.getSenderAddr(),msg0.getMessage());
							break ;
						}
						case BaseMessage.CLINT_TO_SERVER_CHAT:
						{

							break ;
						}
						case BaseMessage.CLINT_TO_SERVER_REFRESHFRIENDS:
						{
							ClientRefreshFriendsMessage msg0 = (ClientRefreshFriendsMessage) msg;
							ui.refreshFriendList(msg0.getFriendsList());
							break ;
						}
						case BaseMessage.SERVER_TO_CLINT_FiLE:
						{
							System.out.println("收到文件消息");
							ServerClientFile msg1 = (ServerClientFile) msg;
							recfile(msg1);

						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//发送消息
	public void sendMessage(BaseMessage msg) throws Exception {
		s.getOutputStream().write(msg.popPack());
		s.getOutputStream().flush();
	}
	//发送文件
	public void  sendFile(ClientServerFile mes) throws Exception {
		//获取文件名
		String path = mes.getFilename();
		//绝对路径
		File file = new File(path);
		//输入流读取文件
		FileInputStream fis = new FileInputStream(file);
		//输出流发送文件
		OutputStream os = s.getOutputStream();
		//写出消息类型
		os.write(mes.getMessageType());
		//写出文件名长度
		os.write(file.getName().length());
		//写出文件名
		os.write(file.getName().getBytes());
		byte[] bytes = new byte[1024];
		int len = 0;
		while ((len=fis.read(bytes))!=-1){
			//写出文件内容
			os.write(bytes,0,len);
		}

	}
	public void recfile(ServerClientFile mes) throws IOException {
		String filename = mes.getFilename();
		String path = "e:/jieshou";
		File file = new File(path, filename);
		OutputStream os = s.getOutputStream();
		InputStream in = s.getInputStream();
		byte[] bytes = new byte[1024];
		int len = 0;
		while ((len = in.read(bytes)) != -1) {
			os.write(bytes,0,len);
		}
		System.out.println("接受完成");
		os.close();
		in.close();
	}

}
