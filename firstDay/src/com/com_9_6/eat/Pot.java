package com_9_6.eat;

public class Pot {
    private int max = 100;
    public synchronized int remove(int i,int how) throws Exception {
        while (how<=4){
            max-=i;
            this.notify();
            }
            this.wait();
       return max;
    }
}
