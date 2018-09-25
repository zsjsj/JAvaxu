package Reflect;

public class Test {
    public static void main(String[] args) {
        Person p1 = new Person("zhangsan", 20);
        Person p2 = new Person();
        Person p3 = new Person();

        System.out.println(p2);
        System.out.println(p3);

        try {
            CopyObjUtil.copyobj(p1,p2);
            CopyObjUtil.copyPropertiesInIntrospector2(p1,p3);

            System.out.println(p2);
            System.out.println(p3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
