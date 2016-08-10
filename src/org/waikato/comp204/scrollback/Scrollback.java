package org.waikato.comp204.scrollback;
import java.util.List;

/**
 * A String Scrollback implementation
 *
 * This implementation is case insensitive
 */
public class Scrollback implements ScrollbackInterface {

    private List<String> history;
    private static final int _defaultSize  = 10;
    private int historyCapacity;

    public Scrollback() {
        this(_defaultSize);
    }
    public Scrollback(int capacity) {
        historyCapacity = capacity;
        this.history = new FixedList<String>(capacity);
    }

    @Override
    public void add(String item){
        if(item == null || item.trim().equals("")) throw new IllegalArgumentException("The Item must Contain a value.");

        if(!isValid(item)) return;

        try{
            history.add(item);
        }catch (IndexOutOfBoundsException ex){  //List is fill.
            history.remove(0);                  //Solution: remove oldest value
            add(item);
        }

    }
    public String getPrevious(int Index){
         if(Index < 0) Index = 0;
         return history.get(Index);
    }

    @Override
    public String getLast() {
        return getPrevious(getCount() - 1);
    }

    @Override
    public void clear() {
        history.clear();
    }

    @Override
    public int getCapacity() {
        return historyCapacity;
    }

    @Override
    public int getCount() {
        return history.size();
    }

    private boolean isValid(String item){
        if(!history.isEmpty() && item.equalsIgnoreCase(getLast())) return false;
        return true;
    }

}
