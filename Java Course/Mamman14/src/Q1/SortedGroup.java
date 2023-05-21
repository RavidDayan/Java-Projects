package Q1;

import java.util.ArrayList;

public class SortedGroup <T> implements Comparable<T> {
    private ArrayList<T> array;
    public SortedGroup(){
        array=new ArrayList<>();
    }
    public void add(T object){

    }
    public int remove(T object){
        int counter=0;
        for(int i=array.size()-1;i>=0;i--){
            if(object.equals(array.get(i))){
                array.remove(i);
                counter++;
            }
        }
    return counter;
    }

    @Override
    public int compareTo(T o) {
        return 0;
    }
}
