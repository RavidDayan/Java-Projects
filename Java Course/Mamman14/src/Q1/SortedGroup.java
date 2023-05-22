package Q1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedGroup<T extends Comparable<T>> implements Iterable<T> {
    private ArrayList<T> array;//array the will store the objects of the group

    public SortedGroup() {//constructor
        array = new ArrayList<>();
    }

    public void add(T newObject) {//adds new object to group and sorts it
        boolean isNotAdded=true;
        if (array.size() == 0) {//if there are no objects inserts it to end of list(which is still the first item in group)
            array.add(newObject);
        } else {
            for (int i = 0; i < array.size(); i++) {//
                if (newObject.compareTo(array.get(i)) == 0 || newObject.compareTo(array.get(i)) == -1) {
                    array.add(i, newObject);
                    isNotAdded=false;
                    break;
                }
            }
            if (isNotAdded){
                array.add(newObject);
            }
        }


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

    @Override
    public Iterator<T> iterator() {
        return new GroupIterator<T>(this);
    }


    public int compareTo(T other) {
        return 0;
    }

    private class GroupIterator<T extends Comparable<T>> implements Iterator<T> {
        private int index;
        private SortedGroup<T> group;

        public GroupIterator(SortedGroup<T> group) {
            index = 0;
            this.group = group;
        }

        @Override
        public boolean hasNext() {
            return index < array.size();
        }

        @Override
        public T next() {
            if (hasNext()) {
                T object = group.array.get(index);
                index++;
                return object;

            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
