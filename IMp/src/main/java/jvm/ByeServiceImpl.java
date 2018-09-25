package jvm;

public class ByeServiceImpl implements IByeService {
    @Override
    public void sayBye(String str) {
        System.out.println("byebye"+str);
    }
}
