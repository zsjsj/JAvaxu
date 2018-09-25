package batisTest;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 测试增伤改查
 */
public class TestCRUD {
    @Test
    public void testInsert() throws IOException {
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //创建会话工厂(builder模式)
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        //开启会话，相当于连接
        SqlSession sess = sf.openSession();
        User u = new User() ;
        u.setId(4);
        u.setName("zhangxu");
        u.setAge(22);
        sess.insert("users.insert" , u) ;
        sess.commit();
        sess.close();
        System.out.println("ok");
    }
    @Test
    public void testupdate() throws IOException {
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //创建会话工厂(builder模式)
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        //开启会话，相当于连接
        SqlSession sess = sf.openSession();
        User u = new User();
        u.setName("lijian");
        u.setAge(18);
        u.setId(1);
        sess.update("users.update",u);
        sess.commit();
        sess.close();
        System.out.println("ok");
    }
    @Test
    public void testselect() throws IOException {
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //创建会话工厂(builder模式)
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        //开启会话，相当于连接
        SqlSession sess = sf.openSession();
        List<User> list = sess.selectList("select.select");
        sess.commit();
        sess.close();
    }
    @Test
    public void testSelectAll() throws IOException {
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //创建会话工厂(builder模式)
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        //开启会话，相当于连接
        SqlSession sess = sf.openSession();
        List<User> list = sess.selectList("users.select" ) ;
        sess.commit();
        System.out.println(list.size());
        sess.close();
    }
    public void testdelete() throws IOException {
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //创建会话工厂(builder模式)
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        //开启会话，相当于连接
        SqlSession sess = sf.openSession();
        List<Object> list = sess.selectList("select.select");
        sess.commit();
        sess.close();
        System.out.println("ok");
    }
}





