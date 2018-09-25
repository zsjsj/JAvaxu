package homeWork;

import java.util.ArrayList;

public class Teacher {
    private int id;
    private String name;
    private ArrayList<Student> students= new ArrayList<Student>();

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;

    }
    public void addstudent(Student... student){
        for(Student s : students){
            this.getStudents().add(s) ;
            s.getTeachers().add(this) ;
        }
    }

}
