package myself.server1;

import myself.common1.BaseMessage;
import myself.common1.ClientServerFile;
import myself.common1.ServerClientFile;
import myself.common1.ServerRefreshFriendsMessage;
import util.DataUtil;
import myself.util1.Constant;


import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

public class QqServer {
    private static  QqServer instance;
    private QqServer(){

    }
    public static QqServer getInstance(){
        if (instance!=null){
            return instance;
        }
        synchronized (QqServer.class){
            if (instance==null){
                instance = new QqServer();
            }
        }
        return instance;
    }
    private HashMap<String,SocketChannel>Clients = new HashMap<String,SocketChannel>();
   public void start (){
       try {
           ThreadPoolExecutor pool= (ThreadPoolExecutor) Executors.newFixedThreadPool(Constant.QQ_SERVER_THREAD_POOL_CORES);
           ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(Constant.QQ_SERVER_CHANNEl_BLOCKING_MODE);
           InetSocketAddress addr = new InetSocketAddress(Constant.QQ_SERVER_BIND_HOST, Constant.QQ_SERVER_BIND_PORT);
           ssc.bind(addr);
           Selector sel = Selector.open();
           ssc.register(sel,SelectionKey.OP_ACCEPT);
           for (;;){
               sel.select();
               Iterator<SelectionKey> it = sel.selectedKeys().iterator();
               while (it.hasNext()){
                   SelectionKey key = it.next();
                   try {
                       if (key.isAcceptable()){
                           SocketChannel sc0 = ssc.accept();
                           InetSocketAddress isa = (InetSocketAddress) sc0.getRemoteAddress();
                           String IAddr = isa.getHostName()+isa.getPort();
                           System.out.println(IAddr+"上线了");
                           Clients.put(IAddr,sc0);
                           sc0.configureBlocking(Constant.QQ_SERVER_CHANNEl_BLOCKING_MODE);
                           SelectionKey key1 = sc0.register(sel, SelectionKey.OP_READ);
                           ReentrantLock lock = new ReentrantLock();
                           key1.attach(lock);
                           broadmessage(genFriendListMessage());
                       }
                       //客户端通道
                       if (key.isReadable()){
                           pool.execute(new ProcessMessageTask(key,this));
                       }
                   }catch (Exception e){
                       key.cancel();
                   }finally {
                       it.remove();
                   }
                   //服务器通道
               }
           }

       } catch (IOException e) {
         e.printStackTrace();
       }
   }
   //广播消息
    public void broadmessage(BaseMessage mes){
        System.out.println("广播消息");
        for(SocketChannel sc : Clients.values()){
            try {
                sc.write(ByteBuffer.wrap(mes.popPack())) ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //转发消息
    public void sendMessage(BaseMessage mes, String IAdrr) throws Exception {
        SocketChannel sc = Clients.get(IAdrr);
        if (sc!=null){
            sc.write(ByteBuffer.wrap(mes.popPack()));
        }
    }
    public List<String> getFriendsList(){
        return new ArrayList<String>(Clients.keySet());
    }
    public byte[] getFriendsBytes() throws Exception {
        return DataUtil.serialData(getFriendsList()) ;
    }
    //生成好友列表
    public ServerRefreshFriendsMessage genFriendListMessage() throws Exception {
        ServerRefreshFriendsMessage msg = new ServerRefreshFriendsMessage();
        msg.setFriendsBytes(getFriendsBytes());
        return msg ;
    }
//转发文件
    public void sendFile(ServerClientFile mes) throws IOException {
        System.out.println("群发文件");
        String path = mes.getPath();
        String filename = mes.getFilename();
        File file = new File(path,filename);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len=fis.read(bytes))!=-1){
            //写出文件内容
            baos.write(bytes,0,len);
        }
        byte[] bytes1 = baos.toByteArray();
        for(SocketChannel sc : Clients.values()){
            byte[] buf = new byte[1];
            buf[0]= (byte) mes.getMessageType();
            //写出文件类型
            sc.write(ByteBuffer.wrap(buf));
            //写出文件名长度
            int length = file.getName().length();
            buf[0] = (byte) length;
            sc.write(ByteBuffer.wrap(buf));
            //写出文件名
            sc.write(ByteBuffer.wrap(filename.getBytes()));
            //写出文件内容
            sc.write(ByteBuffer.wrap(bytes1));
            System.out.println("打包完成");
        }

    }
    //接收文件
    public static void recfile(ServerClientFile mes,String filename,SocketChannel sc){
       try {
           File file = new File(mes.getPath(), filename);
           FileOutputStream fos = new FileOutputStream(file);
           ByteBuffer bufs = ByteBuffer.allocate(1024);
           int len = 0;
           while ((len = sc.read(bufs)) != 0) {
               bufs.flip();
               fos.write(bufs.array(), 0, len);
               bufs.clear();
           }
           fos.close();
       }catch (Exception e){
           e.getStackTrace();
       }

    }
}
