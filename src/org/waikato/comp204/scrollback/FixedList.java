package org.waikato.comp204.scrollback;
import java.util.ArrayList;

/**
 * Created by Sven on 8/8/2016.
 */
public class FixedList<T> extends ArrayList<T> {

    private int capacity;

    public FixedList(int capacity) {
       super(capacity);
        this.capacity = capacity;
    }


    @Override
    public boolean add(Object o) {
        if(isFull()) throw new IndexOutOfBoundsException("The List is Fill.");
            return super.add((T)o);
    }

    @Override
    public void clear() {
        super.clear();
    }

    public boolean isFull(){
        return size() >= capacity;
    }

    public int getCapacity(){
        return capacity;
    }

}
