package Reflect;

import java.beans.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CopyObjUtil {
    public static void copyobj(Object src, Object des) throws Exception {
        BeanInfo bi = Introspector.getBeanInfo(src.getClass());
        BeanInfo b2 = Introspector.getBeanInfo(des.getClass());
        PropertyDescriptor[] pp2 = b2.getPropertyDescriptors();
        HashMap<String, Method> ppmap = new HashMap<>();
        for (PropertyDescriptor pp : pp2) {
            Method setter = pp.getWriteMethod();
            String name = pp.getName();
            if (setter!=null){
                ppmap.put(name,setter);
            }
        }
        PropertyDescriptor[] pps = bi.getPropertyDescriptors();
        for (PropertyDescriptor pp : pps) {
            String name = pp.getName();
            Method reader = pp.getReadMethod();
            if (reader==null){
                continue;
            }
            Method setter = ppmap.get(name);
            if (setter==null){
                continue;
            }
            Class[] types = setter.getParameterTypes();
            if (reader.getReturnType()==types[0]&&types.length==1){
                reader.setAccessible(true);
                Object value = reader.invoke(src);
                setter.setAccessible(true);
                setter.invoke(des,value);
            }

        }
 }

    /**
     * 使用内省实现任意两个对象的属性复制
     * 只要返回值类型和参数相同即可。
     */
    public static void copyPropertiesInIntrospector2(Object src, Object dest) throws Exception {
        //源对象的beanInfo
        BeanInfo srcBI = Introspector.getBeanInfo(src.getClass());

        //目标对象
        BeanInfo destBI = Introspector.getBeanInfo(dest.getClass());

        //存放目标类的所有set方法
        Map<String , Method> destSetters = new HashMap<String, Method>() ;
        //目标属性
        PropertyDescriptor[] destPPS = destBI.getPropertyDescriptors();
        for (PropertyDescriptor destPP : destPPS) {
            //属性
            String destPPName = destPP.getName();
            //set方法
            Method setMethod = destPP.getWriteMethod();
            if(setMethod != null){
                destSetters.put(destPPName , setMethod) ;
            }
        }


        //源对象的属性集合
        PropertyDescriptor[] srcPPS = srcBI.getPropertyDescriptors();
        for (PropertyDescriptor pp : srcPPS) {
            //属性名
            String ppname = pp.getName();
            //get方法
            Method getMethod = pp.getReadMethod();
            if (getMethod == null) {
                continue;
            }
            Class retType = getMethod.getReturnType() ;

            Method setMethod = destSetters.get(ppname) ;
            if(setMethod != null){
                Class[] ptypes = setMethod.getParameterTypes();

                if(ptypes != null && ptypes.length == 1 && ptypes[0] == retType){
                    getMethod.setAccessible(true);
                    Object retValue = getMethod.invoke(src) ;
                    setMethod.setAccessible(true);
                    setMethod.invoke(dest,retValue) ;
                }
            }
        }
    }
}
