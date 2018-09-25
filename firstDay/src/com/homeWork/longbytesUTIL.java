package homeWork;

public class longbytesUTIL {
    public static void main(String[]args){

    }
    public static byte[] long2bytes(long l){
        byte[] bytes = new byte[8];

        for (int i =7;i>=0;i--){
            long l1 = l >> (8 * i);
            bytes[7-i]= (byte) l1;
        }
        return bytes;

    }
    public static long bytes2long(byte[] bytes){
        long l=0;
        for (int i =0;i<8;i++){
            long i1 = bytes[i]&0xffL << i * 8;
            l+=i1;
        }
    return l;
    }
}
