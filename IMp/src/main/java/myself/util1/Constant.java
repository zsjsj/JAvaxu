package myself.util1;

import util.PropertiesUtil;

public class Constant {
    //阻塞模式
    private static final String QQ_SERVER_CHANNEl_BLOCKING_MODE_NAME = "qq.server.channel.blocking.mode" ;
    public static final boolean QQ_SERVER_CHANNEl_BLOCKING_MODE = util.PropertiesUtil.getBooleanValue(QQ_SERVER_CHANNEl_BLOCKING_MODE_NAME) ;

    //服务器绑定ip
    private static final String QQ_SERVER_BIND_HOST_NAME = "qq.server.bind.host" ;
    public static final String QQ_SERVER_BIND_HOST = util.PropertiesUtil.getStringValue(QQ_SERVER_BIND_HOST_NAME) ;

    //服务器绑定端口
    private static final String QQ_SERVER_BIND_PORT_NAME = "qq.server.bind.port";
    public static final int QQ_SERVER_BIND_PORT = util.PropertiesUtil.getIntValue(QQ_SERVER_BIND_PORT_NAME);

    //线程池线程数
    private static final String QQ_SERVER_THREAD_POOL_CORES_NAME = "qq.server.thread.pool.cores";
    public static final int QQ_SERVER_THREAD_POOL_CORES= util.PropertiesUtil.getIntValue(QQ_SERVER_THREAD_POOL_CORES_NAME);


    //client绑定远程ip
    private static final String QQ_CLIENT_SERVER_IP_NAME = "qq.client.server.ip";
    public static final String QQ_CLIENT_SERVER_IP = util.PropertiesUtil.getStringValue(QQ_CLIENT_SERVER_IP_NAME);

    //服务器绑定端口
    private static final String QQ_CLIENT_SERVER_PORT_NAME = "qq.client.server.port";
    public static final int QQ_CLIENT_SERVER_PORT = PropertiesUtil.getIntValue(QQ_CLIENT_SERVER_PORT_NAME);


}
