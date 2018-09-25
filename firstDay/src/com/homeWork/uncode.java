package homeWork;

import org.junit.Test;
import sun.rmi.runtime.NewThreadAction;

import java.io.*;

public class uncode {
    @Test
    public void file() throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("e:/ceshi/2.txt",true), "unicode");

        byte[] b1 = new String("ab").getBytes("unicode");
        osw.close();

    }
}
