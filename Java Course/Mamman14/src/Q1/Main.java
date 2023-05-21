package Q1;
import java.util.ArrayList;

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
    public static void main(String[] args){
        System.out.println("hellow");
    }
}
