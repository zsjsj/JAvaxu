package homeWork;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class Count1 {
    //public static long[] longs = new long[7812500];
    public static void main(String[] args){
        int i=0;
        long[] count = count("e:/ceshi/1.txt");
        for (Long aLong : count) {
            int i2 = long2count(aLong);
            i+=i2;
        }
        System.out.println(i);
    }
    public static long[] count(String file)  {
        long[] longs = new long[7812500];
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;
            while ((line=br.readLine())!=null){
                String[] split = line.split(" ");
                for (String s : split) {
                    long l = Long.parseLong(s);
                    distict(l,longs);
                }
            }
            br.close();
        }catch (Exception e){
            e.getStackTrace();
        }
        return longs;
    }
    public static void distict(long i,long[] longs){
        int j = (int) (i / 64);
        int k = (int) (i % 64);
        long b =1;
         b = b << 10;
        if((longs[j]|b)==longs[j]){

        }else {
            longs[j]+=b;
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

