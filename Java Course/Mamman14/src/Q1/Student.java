package Q1;

public class Student implements Comparable<Student> {
    private String name;//students name
    private int id;//students id
    private int grade;//students grade

    public Student(String name, int id, int grade) throws IlegalIdException, IlegalGradeException {//creates a new student only if id and grade are legal
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
    public int compareTo(Student other) {//compares 2 students by their grades, if instance has bigger grade return 1 ,if instance is has smaller grade returns -1 and otherwise they have the same grade and  returns 0;
        if (this.grade > other.grade) {
            return 1;
        }
        if (this.grade < other.grade) {
            return -1;
        }
        return 0;
    }

    private boolean isValidId(int id) {//checks if id is between 0 to 999999999
        return id >= 0 && id <= 999999999;
    }

    private boolean isValidGrade(int grade) {//checks if grade is between 0-100
        return grade >= 0 && grade <= 100;
    }

    @Override
    public boolean equals(Object other) {//checks if 2 Students are equals, recieves object inorder to override object equals method
        if (other instanceof Student) {//checks if object is student, otherwise returns false
            return this.id == ((Student) other).id && this.name.equals(((Student) other).name);//checks if student is the same by name and id
        } else {
            return false;
        }
    }

    @Override
    public String toString() {//returns a string with the name,id and grade of student.
        String returnedString;
        returnedString = "Name: " + name + " Id: " + String.valueOf(id) + " Grade: " + String.valueOf(grade);
        return returnedString;
    }
}
