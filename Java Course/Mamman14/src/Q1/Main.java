package Q1;
import java.util.ArrayList;
import java.util.Iterator;

public class  Main <T extends Comparable<T>>{
    public  SortedGroup<T> reduce(SortedGroup<T> array, T object){
        SortedGroup<T> newArray=new SortedGroup<T>();
        for(T listObject:array){
            if(object.compareTo(listObject)==1){
                newArray.add(listObject);
            }
        }
        return newArray;
    }
    private static void generateStudents(SortedGroup<Student> studentList) throws IlegalIdException, IlegalGradeException {
        studentList.add(new Student("Ravid",123456789,90));
        studentList.add(new Student("shmuel",353446717,80));
        studentList.add(new Student("levi",123456789,70));
        studentList.add(new Student("moshe",123456789,60));
        studentList.add(new Student("yakov",123456789,50));
        studentList.add(new Student("mordechai",123456789,40));
    }
    public static void main(String[] args){
        SortedGroup<Student> studentList=new SortedGroup<>();
        try {
            generateStudents(studentList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Iterator<Student> iterator=studentList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
