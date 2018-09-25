
import org.junit.Test;
import sun.misc.Cleaner;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

public class GC {
    @Test
    public void testGC() throws Exception {
        int n = 1024*1024*500;
        ByteBuffer buf = ByteBuffer.allocateDirect(n);
        for (;;){
            Thread.sleep(10000);
            Class<? extends ByteBuffer> dir = buf.getClass();
            Method cleaner = dir.getDeclaredMethod("cleaner");
            cleaner.setAccessible(true);
            Cleaner cleaner1 = (Cleaner) cleaner.invoke(buf);
            cleaner1.clean();

       }
    }
    @Test
    public void testGC1() throws Exception {
        int n = 1024*1024*500;
        ByteBuffer buf = ByteBuffer.allocateDirect(n);
        for (;;){
            Thread.sleep(10000);
            Class<? extends ByteBuffer> dir = buf.getClass();
            Field cleaner = dir.getDeclaredField("cleaner");
            cleaner.setAccessible(true);

        }
    }
}
