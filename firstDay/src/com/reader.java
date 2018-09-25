import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class reader {
    @Test
    public void readers() throws Exception {
        InputStreamReader insr = new InputStreamReader(new FileInputStream("E:/test/8888.txt"),"UTF-8" );
        char[] chars = new char[2];
        int len =0;
        while ((len=insr.read(chars))!=-1){
            System.out.print(new String(chars,0,len));
        }
    }
}
