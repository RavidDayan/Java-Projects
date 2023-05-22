package Q1;

public class Student implements Comparable<Student> {
    private String name;
    private int id;
    private int grade;

    public Student(String name, int id, int grade) throws IlegalIdException, IlegalGradeException {
        if (!isValidId(id)) {
            throw new IlegalIdException();
        }
        if (!isValidGrade(grade)) {
            throw new IlegalGradeException();
        }
        this.name = name;
        this.id = id;
        this.grade = grade;
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

    private boolean isValidId(int id) {
        return id >= 0 && id <= 999999999;
    }

    private boolean isValidGrade(int grade) {
        return grade >= 0 && grade <= 100;
    }
    @Override
    public boolean equals(Object other) {
        if (other instanceof Student ){
            return this.id == ((Student) other).id && this.name.equals(((Student) other).name);
        }
        else{
            return false;
        }
    }

    @Override
    public String toString() {
        String returnedString;
        returnedString = "Name: " + name + " Id: " + String.valueOf(id)+ " Grade: " + String.valueOf(grade);
        return returnedString;
    }
}
