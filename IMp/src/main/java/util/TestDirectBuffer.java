package util;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestDirectBuffer {
    @Test
    public void copyTest()throws Exception{
        FileInputStream fis = new FileInputStream("e:/ceshi/123.jpg");
        FileChannel in = fis.getChannel();
        FileOutputStream fos = new FileOutputStream("e:/ceshi/out/123");
        FileChannel out = fos.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024*1024*8);
        int len = 0;
        long start = System.currentTimeMillis();
        while ((len=in.read(buf))!=-1){
            buf.flip();
            out.write(buf);
            buf.clear();
        }
        fis.close();
        in.close();
        fos.close();
        out.close();
        System.out.println(System.currentTimeMillis()-start);
    }
    @Test
    public void copyTestDir()throws Exception{
        FileInputStream fis = new FileInputStream("e:/ceshi/123.jpg");
        FileChannel in = fis.getChannel();
        FileOutputStream fos = new FileOutputStream("e:/ceshi/out/123.jpg");
        FileChannel out = fos.getChannel();
        ByteBuffer buf = ByteBuffer.allocateDirect(1024*1024*8);
        int len = 0;
        long start = System.currentTimeMillis();
        while ((len=in.read(buf))!=-1){
            buf.flip();
            out.write(buf);
            buf.clear();
        }
        fis.close();
        in.close();
        fos.close();
        out.close();
        System.out.println(System.currentTimeMillis()-start);
    }
    @Test
    public void copyTestZero()throws Exception{
        File f = new File("e:/ceshi/1.mp4");
        FileInputStream fis = new FileInputStream(f);
        FileChannel in = fis.getChannel();
        FileOutputStream fos = new FileOutputStream("e:/ceshi/out/1.jpg");
        FileChannel out = fos.getChannel();
        long start = System.currentTimeMillis();
        in.transferTo(0,f.length(),out);
        fis.close();
        in.close();
        fos.close();
        out.close();
        System.out.println(System.currentTimeMillis()-start);
    }
}
