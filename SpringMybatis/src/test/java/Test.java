import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
   @org.junit.Test
   public  void test1() throws SQLException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml") ;
        DataSource ds = (DataSource) ac.getBean("dataSource");
        Connection conn = ds.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select version()") ;
        if(rs.next()){
            String version  = rs.getString(1);
            System.out.println(version);
        }
        rs.close();
        conn.close();
    }
}
