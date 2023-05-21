import java.util.ArrayList;

public class SortedGroup <T> implements Comparable<T> {
    ArrayList<T> array;
    public SortedGroup(){
        array=new ArrayList<>();
    }

    @Override
    public int compareTo(T o) {
        return 0;
    }
}
