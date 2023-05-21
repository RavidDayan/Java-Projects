package Q1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedGroup<T> implements Comparable<T>, Iterable<T> {
    private ArrayList<T> array;

    public SortedGroup() {
        array = new ArrayList<>();
    }

    public void add(T object) {

    }

    public int remove(T object) {
        int counter = 0;
        for (int i = array.size() - 1; i >= 0; i--) {
            if (object.equals(array.get(i))) {
                array.remove(i);
                counter++;
            }
        }
        return counter;
    }

    public Iterator<T> iterator() {
        return new GroupIterator<T>();
    }

    @Override
    public int compareTo(T o) {
        return 0;
    }

    private class GroupIterator<T> implements Iterator {
        private int index;

        public GroupIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < array.size();
        }

        @Override
        public T next() {
            if (hasNext()) {
                index++;
                return (T) array.get(index++);
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
