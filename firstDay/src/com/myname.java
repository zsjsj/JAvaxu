import org.junit.Test;

public class myname {

    public static void main(String[] args){
       char a = '中';
        chartounicode(a);
        int i = 0;
        intto2(i);
        intto16(i);

    }

    public static void chartounicode(char a){
        StringBuilder s = new StringBuilder();
        int i = a;
        char[] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        s.append(chars[i>>12&0xf]);
        s.append(chars[i>>8&0xf]);
        s.append(chars[i>>4&0xf]);
        s.append(chars[i>>0&0xf]);
        System.out.println(s.toString());
    }
    public static void intto2(int i){
        StringBuilder s = new StringBuilder();
        char[] chars = {'0','1'};
        for (int j=31;j>=0;j--){
            s.append(chars[i>>1*j&1]);
        }
        System.out.println(s.toString());
    }
    public static void intto16(int i){
        StringBuilder s = new StringBuilder();
        char[] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        for (int j=7;j>=0;j--){
            s.append(chars[i>>4*j&0xf]);
        }
        System.out.println(s.toString());
    }
    @Test
    public void byteto(){
        byte b = -1;
        System.out.println(b&0xff);
    }


}
