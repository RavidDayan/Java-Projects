package Q1;
import java.util.Iterator;
public class Main<T extends Comparable<T>> {

    //was put randomly in this class because mamman didn't state where to put
    public SortedGroup<T> reduce(SortedGroup<T> array, T object) {//received a list and object of the same type and makes a new list with only objects compared more than the received object
        SortedGroup<T> newArray = new SortedGroup<T>();
        for (T listObject : array) {
            if (object.compareTo(listObject) == -1) {
                newArray.add(listObject);
            }
        }
        return newArray;
    }

    private static void generateStudents(SortedGroup<Student> studentList) throws IlegalIdException, IlegalGradeException {//adds custom made students to list to show add works correctly
        studentList.add(new Student("Ravid", 123456789, 90));
        studentList.add(new Student("shmuel", 353446717, 80));
        studentList.add(new Student("levi", 123456789, 70));
        studentList.add(new Student("moshe", 123456789, 60));
        studentList.add(new Student("yakov", 123456789, 50));
        studentList.add(new Student("mordechai", 123456789, 40));
        studentList.add(new Student("Moran", 123456789, 30));
    }

    private static void printStudents(SortedGroup<Student> studentList) {//prints all student in list,show iterator works correctly
        Iterator<Student> iterator = studentList.iterator();
        System.out.println("Current student list:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println();
    }

    public static void main(String[] args) throws IlegalIdException, IlegalGradeException {
        Main main = new Main();
        SortedGroup<Student> studentList = new SortedGroup<>();
        try {
            generateStudents(studentList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        printStudents(studentList);
        printStudents(main.reduce(studentList, new Student("minimumTestGrade", 123456789, 60)));
        System.out.println("removed "+studentList.remove(new Student("Moran", 123456789, 30))+" students");//shows remove works correctly
        printStudents(studentList);

    }
}
