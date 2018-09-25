package homeWork;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestMany2many {
    @Test
    public void testInsertW() throws IOException {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        SqlSession sess = sf.openSession();
        //创建对象
        Teacher t1 = new Teacher();
        Teacher t2 = new Teacher();

        Student s1 = new Student();
        Student s2 = new Student();
        Student s3 = new Student();
        Student s4 = new Student();

        //设置关联
        t1.addstudent(s1, s2, s3);
        t2.addstudent(s2, s3, s4);

        sess.insert("teacher.insert", t1);
        sess.insert("teacher.insert", t2);

        sess.insert("teacher.insert", s1);
        sess.insert("teacher.insert", s2);
        sess.insert("teacher.insert", s3);
        sess.insert("teacher.insert", s4);

        //插入关系
        sess.insert("teacher.insertLink", t1);
        sess.insert("teacher.insertLink", t2);

        sess.commit();
    }
}
