package Q1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedGroup<T extends Comparable<T>> implements Iterable<T> {
    private ArrayList<T> array;//array the will store the objects of the group

    public SortedGroup() {//constructor
        array = new ArrayList<>();
    }

    public void add(T newObject) {//adds new object to group and sorts it, in case new object is compared the same as other it goes to the bottom of the same as him in the list
        boolean isNotAdded = true;//indicator if the new object has been added between the existing group or not
        if (array.size() == 0) {//if there are no objects inserts it to end of list(which is still the first item in group)
            array.add(newObject);
        } else {
            for (int i = 0; i < array.size(); i++) {//goes over list to see how the object compares to other objects already in list,incase its the same or compared below others its added there and stops looking for a place
                if (newObject.compareTo(array.get(i)) == 0 || newObject.compareTo(array.get(i)) == -1) {
                    array.add(i, newObject);
                    isNotAdded = false;
                    break;
                }
            }
            if (isNotAdded) {//incase we did not find  where to add new object in between list it means object is compared bigger than others.
                array.add(newObject);
            }
        }
    }

    public int remove(T object) {//removes all instances of objects equal to removed object
        int counter = 0;//counter to count how many removes objects
        for (int i = array.size() - 1; i >= 0; i--) {//delets from the last item in list to the first one to avoid index out of bounds exception while deleting
            if (object.equals(array.get(i))) {
                array.remove(i);
                counter++;
            }
        }
        return counter;
    }

    @Override
    public Iterator<T> iterator() {//returns new custom iterator
        return new GroupIterator<T>(this);
    }
    private class GroupIterator<T extends Comparable<T>> implements Iterator<T> {//custom iterator for sorted group class
        private int index;//index of iterator
        private SortedGroup<T> group;//list iterator iterates over

        public GroupIterator(SortedGroup<T> group) {//constructor
            index = 0;
            this.group = group;
        }

        @Override
        public boolean hasNext() {//checks if there is another item in list after current index
            return index < array.size();
        }

        @Override
        public T next() {
            if (hasNext()) {//if there is another object after current returns the next one in the list
                T object = group.array.get(index);
                index++;
                return object;

            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
