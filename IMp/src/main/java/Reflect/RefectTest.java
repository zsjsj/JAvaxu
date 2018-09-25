package Reflect;

import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by Administrator on 2018/9/13.
 */
public class RefectTest {
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
        Constructor c1 = clazz.getDeclaredConstructor(Integer[][].class) ;
        Class clz = int[][].class ;

        c1.setAccessible(true);
        Integer[][] arr = {} ;
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
    public void testCopy() throws Exception {
        JiafeiCat c1 = new JiafeiCat();
        c1.setColor("black");
        c1.setName("kk");
        JiafeiCat c2 = new JiafeiCat();
        copyPropertiesInMethod(c1 , c2);
        System.out.println();
    }

    /**
     * 实现两只猫属性的复制
     */
    private static void copyPropertiesInField(JiafeiCat c1 , JiafeiCat c2) throws Exception {
        Class clz = JiafeiCat.class ;
        //通过字段复制,得到定义的字段
        Field[] fs = clz.getDeclaredFields();
        for(Field f : fs){
            f.setAccessible(true);
            Object value = f.get(c1) ;
            f.set(c2,value) ;
        }
    }
    /**
     * 实现两只猫属性的复制
     */
    private static void copyPropertiesInMethod(JiafeiCat c1 , JiafeiCat c2) throws Exception {
        Class clz = JiafeiCat.class ;
        //通过字段复制,得到定义的字段
        Method[] ms = clz.getMethods();
        for(Method m : ms){
            //得到方法名
            String mname = m.getName();
            //参数类型
            Class[] ptypes = m.getParameterTypes() ;
            //返回值类型
            Class retType = m.getReturnType() ;

            //提取标准javabean方法
            if(mname.startsWith("get")
                    && (ptypes == null || ptypes.length == 0)){
                m.setAccessible(true);
                Object retValue = m.invoke(c1);
                String setter = mname.replace("get" , "set") ;
                try {
                    Method setMethod = c2.getClass().getMethod(setter , retType) ;
                    setMethod.setAccessible(true);
                    setMethod.invoke(c2 , retValue ) ;
                } catch (Exception e) {
                    System.out.println(setter + " : " + e.getMessage());
                }
            }
        }
    }

    @Test
    public void testIntrospect() throws IntrospectionException {
        BeanInfo bi = Introspector.getBeanInfo(Cat.class);
        PropertyDescriptor[] pps = bi.getPropertyDescriptors();
        for (PropertyDescriptor pp : pps) {
            Method getter = pp.getReadMethod();
            Method setter = pp.getWriteMethod();
            System.out.println("getter:" + getter + ",setter:" + setter);
        }
    }
    @Test
    public void testIntrospect2() throws Exception {
        JiafeiCat c1 = new JiafeiCat();
        c1.setColor("black");
        c1.setName("kk");
        JiafeiCat c2 = new JiafeiCat();
        copyPropertiesInIntrospector(c1, c2);
        System.out.println();
    }

    /**
     * 使用内省实现复制 , 专用与javabean
     */
    private static void copyPropertiesInIntrospector(JiafeiCat c1, JiafeiCat c2) throws Exception {
        //bean信息
        BeanInfo bi = Introspector.getBeanInfo(c1.getClass()) ;
        PropertyDescriptor[] pps = bi.getPropertyDescriptors();
        for(PropertyDescriptor pp : pps){
            Method getter = pp.getReadMethod();
            Method setter = pp.getWriteMethod() ;
            if(getter != null){
                Class retType = getter.getReturnType();
                Object retValue = getter.invoke(c1 ) ;
                if(setter != null ){
                    setter.invoke(c2 , retValue) ;
                }
            }
        }
    }
}
