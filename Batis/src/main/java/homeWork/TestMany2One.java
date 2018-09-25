package homeWork;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 测试多对一关系
 */
public class TestMany2One {
	@Test
	public void testInsertH() throws IOException {
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
		SqlSession sess = sf.openSession();
        Wife w = new Wife();
        w.setId(2);
        Husband h = new Husband();
        h.setHname("tom");
        h.setWife(w);
        sess.insert("husband.insert" , h) ;
		sess.commit();
		sess.close();
	}
    @Test
    public void testInsertW() throws IOException {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        SqlSession sess = sf.openSession();
        Husband h = new Husband();
        Wife w = new Wife();
        w.setWname("lili");
        w.setHid(1);
        w.setHusband(h);
        sess.insert("wife.insert" , w) ;
        sess.commit();
        sess.close();
    }
	@Test
	public void testStudentandTeacher() throws IOException {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        SqlSession sess = sf.openSession();
		Student o = (Student) sess.selectList("student.selectOne", 1);
		sess.commit();
		sess.close();
	}
}
