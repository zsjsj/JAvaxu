package myPool;

import java.sql.*;

public class MyConnection extends ConnectionAdatpor {
    private Connection con;

    private ConnectionPool pool;
    public MyConnection(Connection con,ConnectionPool pool){
        this.pool = pool;
        this.con = con;
    }
    @Override
    public Statement createStatement() throws SQLException {
        return super.createStatement();
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return super.prepareStatement(sql);
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return super.prepareCall(sql);
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        return super.nativeSQL(sql);
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        super.setAutoCommit(autoCommit);
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return super.getAutoCommit();
    }

    @Override
    public void commit() throws SQLException {
        super.commit();
    }

    @Override
    public void rollback() throws SQLException {
        super.rollback();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return super.isClosed();
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return super.getMetaData();
    }
    @Override
    public void close() throws SQLException {
        pool.backConn(this);
    }

}
