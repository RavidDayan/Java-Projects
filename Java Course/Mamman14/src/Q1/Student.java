package Q1;

public class Student implements Comparable<Student> {
    private String name;
    private int id;
    private int grade;

    public Student(String name,int id,int grade){
      this.grade=grade;

    }

    @Override
    public int compareTo(Student other) {
        if (this.grade > other.grade) {
            return 1;
        }
        if (this.grade < other.grade) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        String returnedString;
        returnedString = "Name: " + name + " Id: " + String.valueOf(id);
        return returnedString;
    }
}
