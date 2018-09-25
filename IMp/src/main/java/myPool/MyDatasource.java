package myPool;



import java.sql.Connection;
import java.sql.SQLException;

public class MyDatasource extends DataSourceAdaptor{

    @Override
    public Connection getConnection() throws SQLException {
        try {
            return ConnectionPool.getinstance().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }
}
