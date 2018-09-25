package myPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPool extends ConnectionAdatpor {
    private static final int MAX = 3;
    private static final int STAR = 2;
    private  AtomicInteger busi = new AtomicInteger(0);
    private static ConnectionPool instance;
    public static LinkedList<Connection> list = new LinkedList();
    private ConnectionPool(){
        initPool();
    }
    public static ConnectionPool getinstance(){
        if (instance != null){
            return instance;
        }
        synchronized (ConnectionPool.class){
            if (instance == null){
                instance = new ConnectionPool();
            }
        }
        return instance;
    }
    /**
     * 初始化池子
     */
    private void initPool() {
        for(int i = 0 ; i < STAR ; i ++){
            Connection con = openNewConnection();
            if(con != null){
                list.add(con) ;
            }
        }
 }
    public Connection openNewConnection(){
        try {
             Class.forName("com.mysql.jdbc.Driver");
             String url = "jdbc:mysql://localhost:3306/big12";
             String user = "root";
             String password = "512719";
            Connection con = DriverManager.getConnection(url, user, password);
            return new MyConnection(con , this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Connection getConnection() throws Exception {
        if (!list.isEmpty()){
            busi.incrementAndGet();
            return list.remove();
        }
        while(busi.get()>=MAX){
            this.wait(10);
        }
        Connection con = openNewConnection();
        busi.incrementAndGet();
        return con;
    }

    public void backConn(MyConnection myConnection) {
        list.add(myConnection);
        busi.decrementAndGet();
        this.notify();
    }
}
