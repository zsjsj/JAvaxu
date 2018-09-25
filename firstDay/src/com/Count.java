
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;


public class Count {
    //public static long[] longs = new long[7812500];
    public static void main(String[] args){
        int i=0;
        LinkedList<Long> count = count("e:/ceshi/1.txt");
        for (Long aLong : count) {
            int i2 = long2count(aLong);
            i+=i2;
        }
        System.out.println(i);
    }
    public static LinkedList<Long> count(String file)  {
        LinkedList<Long> longs = new  LinkedList<Long>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;
            while ((line=br.readLine())!=null){
                String[] split = line.split(" ");
                for (String s : split) {
                    int i = Integer.parseInt(s);
                    distict(i,longs);
                }
            }
            br.close();
        }catch (Exception e){
            e.getStackTrace();
        }
        return longs;
    }
        public static void distict(int i,LinkedList<Long> longs){
        int j = i / 64;
        int k = i % 64;
        long b =1;
        b = b<<k;
        if (longs.size()<j+1){
            for (int a = longs.size();a<=j;a++){
                long l = 0;
                longs.add(l);
            }
             longs.set(j, (long) b);
        }else {
            Long aLong = longs.get(j);
            if ((aLong|b)==aLong){

            }else {
                aLong  = aLong+b;
                longs.set(j,aLong);
            }

        }
    }
    public static int long2count(long l){
        int a=0;
        for (int i = 1;i <=64;i++){
            long l1 = l >> 64 - i&1;
            if (l1==1){
                a+=1;
            }
        }
        return a;
    }
}
