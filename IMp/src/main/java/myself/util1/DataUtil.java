package myself.util1;

import java.io.*;

public class DataUtil {
    public static int bytes2int(byte[] bytes){
        int x;
        x = ((bytes[0] & 0xff) << 24)|((bytes[1] & 0xff) << 16)|((bytes[2] & 0xff )<< 8)|(bytes[3] & 0xff) << 0;
        return x;
        }
    public static byte[] int2bytes(int i){
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (i>>24);
        bytes[1] = (byte) (i>>16);
        bytes[2] = (byte) (i>>8);
        bytes[3] = (byte) (i>>0);
        return bytes;
    }
    public static byte[] seriliza(Object src) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream obos = new ObjectOutputStream(baos);
       obos.writeObject(src);
       baos.close();
       obos.close();
       return baos.toByteArray();
    }
    public static Object desseri(byte[] bytes) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object o = ois.readObject();
        bais.close();
        ois.close();
        return o;
    }
    public static Object deeplyCopy(Object src) throws Exception {
        //串行
        ByteArrayOutputStream baos = new ByteArrayOutputStream() ;
        ObjectOutputStream oos = new ObjectOutputStream(baos) ;
        oos.writeObject(src);
        oos.close();
        baos.close();

        //反串
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray()) ;
        ObjectInputStream ois = new ObjectInputStream(bais) ;
        Object copy = ois.readObject() ;
        ois.close();
        bais.close();
        return copy ;
    }
}
