package util;

public class TestJVM {
    public static void main(String[] args) {
        callself(1);
    }
    public static void callself(int n){
        int x=3;
        int y =4;
        Person p = new Person();
        System.out.println(n);
        n++;
        callself(n);
    }
}
