package Q1;

public class Student implements Comparable<Student> {
    private String name;
    private int grade;

    @Override
    public int compareTo(Student other) {
        if (this.grade>other.grade){
            return 1;
        }
        if (this.grade<other.grade){
            return -1;
        }
        return 0;
    }
}
