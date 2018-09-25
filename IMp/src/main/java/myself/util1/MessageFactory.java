package myself.util1;


import myself.common1.*;
import myself.server1.QqServer;
import server.QQServer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;

public class MessageFactory {
    public static BaseMessage parsemessagefromchannel(SocketChannel sc) throws Exception {
        ByteBuffer buf1 = ByteBuffer.allocate(1);
        sc.read(buf1);
        buf1.flip();
        int messtype = buf1.get(0);
        switch (messtype) {
            case BaseMessage.CLINT_TO_SERVER_CHATS:{
                ServerChatsMessage mes = new ServerChatsMessage();
                ByteBuffer buf4 = ByteBuffer.allocate(4);
                sc.read(buf4);
                buf4.flip();
                int meslen = DataUtil.bytes2int(buf4.array());
                ByteBuffer bufn = ByteBuffer.allocate(meslen);
                sc.read(bufn);
                bufn.flip();
                mes.setMessageBytes(bufn.array());
                mes.setSenderAddrBytes(QQUtil.getRemotAddrBytes(sc.socket()));
                return mes;
            }
            case BaseMessage.CLINT_TO_SERVER_CHAT: {
                ServerChatMessage msg = new ServerChatMessage();
                //接受者地址长度
                buf1.clear();
                sc.read(buf1);
                buf1.flip();
                int recvAddrLen = buf1.get(0);
                //接受者地址
                ByteBuffer bufn = ByteBuffer.allocate(recvAddrLen);
                sc.read(bufn);
                bufn.flip();
                msg.setRecvAddrBytes(bufn.array());

                //发送者地址
                msg.setSenderAddrBytes(util.QQUtil.getRemoteAddrBytes(sc.socket()));

                //消息长度
                ByteBuffer buf4 = ByteBuffer.allocate(4);
                sc.read(buf4);
                buf4.flip();
                int msgLen = util.DataUtil.bytes2Int(buf4.array());

                //消息内容
                bufn = ByteBuffer.allocate(msgLen);
                sc.read(bufn);
                bufn.flip();
                msg.setMessageBytes(bufn.array());
                return msg;
            }
            case BaseMessage.CLINT_TO_SERVER_REFRESHFRIENDS: {
                ServerRefreshFriendsMessage msg = new ServerRefreshFriendsMessage();
                msg.setFriendsBytes(QQServer.getInstance().getFriendsBytes());
                return msg;
            }
            case BaseMessage.CLINT_TO_SERVER_FILE: {
                ServerClientFile mes = new ServerClientFile();
                ByteBuffer buf = ByteBuffer.allocate(1);
                sc.read(buf);
                buf.flip();
                int filenamelen = buf.get(0);
                ByteBuffer bufn = ByteBuffer.allocate(filenamelen);
                sc.read(bufn);
                String filename = new java.lang.String(bufn.array());
                mes.setFilename(filename);
                mes.setPath();
                QqServer.recfile(mes,filename,sc);
                System.out.println("文件接收完成,开始转发");
                return mes;
            }
        }
        return  null;
    }
    /**
     * 从socket中解析服务器消息
     */
    public static BaseMessage parseServerMesageFromSocket(Socket s) throws Exception {
        //
        InputStream in = s.getInputStream();
        //消息类型
        byte[] bytes1 = new byte[1] ;
        try {
            in.read(bytes1) ;
        }catch (Exception e){
            e.getStackTrace();
        }

        int msgType = bytes1[0] ;

        switch (msgType){
            //群聊

            case BaseMessage.SERVER_TO_CLINT_CHATS:

            {

                in.read(bytes1);
                int senderAddrLen = bytes1[0];
                //读取发送地址
                byte[] bytesn = new byte[senderAddrLen];
                in.read(bytesn);
                //发送地址
                String senderAddrStr = new String(bytesn);

                byte[] bytes4 = new byte[4];
                in.read(bytes4);
                int msgLen = DataUtil.bytes2int(bytes4);

                bytesn = new byte[msgLen];
                in.read(bytesn);
                String msg = new String(bytesn);

                ClientChatsMessage msg0 = new ClientChatsMessage();
                msg0.setMessage(msg);
                msg0.setSenderAddr(senderAddrStr);
                return msg0;
            }

            //私聊
            case BaseMessage.SERVER_TO_CLINT_CHAT:
            {
                //
                in.read(bytes1);
                int senderAddrLen = bytes1[0];
                //读取发送地址
                byte[] bytesn = new byte[senderAddrLen];
                in.read(bytesn);
                //发送地址
                String senderAddrStr = new String(bytesn);

                byte[] bytes4 = new byte[4];
                in.read(bytes4);
                int msgLen = DataUtil.bytes2int(bytes4);
                bytesn = new byte[msgLen];
                in.read(bytesn);
                String msg = new String(bytesn);
                ClientChatMessage msg0 = new ClientChatMessage();
                msg0.setMessage(msg);
                msg0.setSenderAddr(senderAddrStr);
                return msg0;
            }
            //好友列表

            case BaseMessage.SERVER_TO_CLINT_FRIENDS:
            {
                System.out.println("收到服务器文件");
                byte[] bytes4 = new byte[4];
                in.read(bytes4);
                int friendsDataLen = DataUtil.bytes2int(bytes4);
                byte[] bytesn = new byte[friendsDataLen];
                in.read(bytesn);
                List<String> friends = (List<String>)DataUtil.desseri(bytesn);
                ClientRefreshFriendsMessage msg = new ClientRefreshFriendsMessage() ;
                msg.setFriendsList(friends);
                return msg ;
            }
            //收到服务器发来文件
            case BaseMessage.SERVER_TO_CLINT_FiLE:
            {
                byte[] bytes = new byte[1];
                int filenamelen = in.read(bytes);
                byte[] bytes2 = new byte[filenamelen];
                in.read(bytes2);
                String filename = new String(bytes2);
                ServerClientFile mes = new ServerClientFile();
                mes.setFilename(filename);
                return mes;
            }
        }
        return null ;
    }
}
