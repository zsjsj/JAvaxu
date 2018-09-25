package homeWork;

import java.util.ArrayList;

public class Relation {
    private int sid;
    private int tid;
    private ArrayList<Teacher> teachers= new ArrayList<Teacher>();
    private ArrayList<Student> students= new ArrayList<Student>();

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public ArrayList<Teacher> getList() {
        return teachers;
    }

    public void setList(ArrayList<Teacher> list) {
        this.teachers = list;
    }

    public ArrayList<Student> getList1() {
        return students;
    }

    public void setList1(ArrayList<Student> list1) {
        this.students = list1;
    }
}
