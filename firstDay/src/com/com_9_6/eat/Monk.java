package com_9_6.eat;

public class Monk extends Thread{
    private int how = 0;
    public Pot pot;
    public Monk() {
    }

    public int getHow() {
        return how;
    }

    public void setHow(int how) {
        this.how = how;
    }

    @Override
    public void run() {
        try {
            pot.remove(1,how);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setHow(how++);
    }
}
