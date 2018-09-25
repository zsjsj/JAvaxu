package myself.util1;

import java.net.InetSocketAddress;
import java.net.Socket;

public class QQUtil {
    //获取远端主机名
    public static String getRemoteHost(Socket s){
        String hostName = ((InetSocketAddress) s.getRemoteSocketAddress()).getHostName();
        return hostName;
    }
    //获取远端端口号
    public static int getRemotePort(Socket s){
        int p = ((InetSocketAddress) s.getRemoteSocketAddress()).getPort();
        return p;
    }
    //获取远端地址
    public static String getRemotAddr(Socket s){
     return   getRemoteHost(s)+ ":" +getRemotePort(s);
    }
    //获取远端地址的字节数组
    public static byte[] getRemotAddrBytes(Socket s){
       return getRemotAddr(s).getBytes();
    }
    //获取本地地址
    public static String getLocalAddr(Socket s){
        String localAddr = s.getLocalAddress().getHostAddress() + s.getLocalPort();
        return localAddr;
    }
}
