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
    public String toString() {
        String returnedString;
        returnedString = "Name: " + name + " Id: " + String.valueOf(id);
        return returnedString;
    }
}
