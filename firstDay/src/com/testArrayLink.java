import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

public class testArrayLink {
    @Test
    public void test(){
        addArray();
        addLink();
    }

    public static void addArray(){
        ArrayList<Object> objects = new ArrayList<Object>();
        long start = System.currentTimeMillis();
        for (int i =0 ;i< 10000000;i++){
            objects.add(i);
        }
        long finish = System.currentTimeMillis();
        long cha = finish-start;
        System.out.println(cha);
    }
    public static void addLink(){
        LinkedList<Object> objects = new LinkedList<Object>();
        long start = System.currentTimeMillis();
        for (int i =0 ;i< 10000000;i++){
            objects.addLast(i);
        }
        long finish = System.currentTimeMillis();
        long cha = finish-start;
        System.out.println(cha);
    }
}
