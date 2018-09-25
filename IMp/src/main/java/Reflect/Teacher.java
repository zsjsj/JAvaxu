package Reflect;

public class Teacher extends Person {
    private String male;

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "male='" + male + '\'' +
                '}';
    }
}
