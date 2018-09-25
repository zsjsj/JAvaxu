package Reflect;


import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by Administrator on 2018/9/13.
 */
public class TestReflect {
	@Test
	public void testNewObject() throws Exception {
		//创建对象
		//Person p = new Person("tom") ;

		//获得类描述符
		Class clazz = Person.class ;
		//实例化对象
		Object obj = clazz.newInstance() ;
		System.out.println();

	}

	/**
	 * 测试构造器
	 */
	@Test
	public void testConstructor() throws Exception {
		Class clazz = Class.forName("com.oldboy.java.domain.Person") ;
		Constructor c1 = clazz.getDeclaredConstructor(int[].class) ;
		c1.setAccessible(true);
		int[] arr = new int[2] ;
		Object obj = c1.newInstance(arr) ;
		System.out.println();

	}
	/**
	 * 测试构造器
	 */
	@Test
	public void testMethod() throws Exception {
		Class clazz = Class.forName("com.oldboy.java.domain.Person") ;
		Object o = clazz.newInstance() ;
		Method m = clazz.getDeclaredMethod("setName" , String.class) ;
		m.setAccessible(true);
		m.invoke(o , "zzzz") ;
	}
	/**
	 * 测试构造器
	 */
	@Test
	public void testField() throws Exception {
		Class clazz = Class.forName("com.oldboy.java.domain.Person") ;
		Object o = clazz.newInstance() ;
		Field f = clazz.getDeclaredField("name") ;
		f.setAccessible(true);
		f.set(o , "jerry");
		System.out.println();
	}

	/**
	 * 修饰符
	 */
	@Test
	public void testModifier() throws Exception {
		Class clazz = Class.forName("com.oldboy.java.domain.Person") ;
		Object o = clazz.newInstance() ;
		Field f = clazz.getDeclaredField("name") ;
		//修饰符总和
		int mod = f.getModifiers();

		System.out.println(Modifier.isPublic(mod));
	}
	@Test
	public void testextend() throws Exception {
		Class<?> clazzt = Class.forName("Reflect.Teacher");
		Class<?> clazzp = Class.forName("Reflect.Person");
		Constructor<?> c1 = clazzp.getConstructor(String.class);
		c1.setAccessible(true);
		Object p = c1.newInstance("zhangsan");
		Object t = clazzt.newInstance();
		//Field name = clazzt.getDeclaredField("name");
		//Object pname = name.get("name");
		Field male = clazzt.getDeclaredField("male");
		Field name = clazzt.getField("name");
		name.setAccessible(true);
		male.setAccessible(true);
		//name.setAccessible(true);
		name.set(t,"jery");
		male.set(t,"nan");
		System.out.println(t);
		//name.set(t,"jery");

	}
}
