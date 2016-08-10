package org.waikato.comp204.scrollback;
import org.waikato.comp204.misc.PokemonEncounter;

import java.util.List;


/**
 * A Generic Scrollback implementation
 */
public class GenericScrollback<T> implements GenericScrollbackInterface<T> {
    private List<T> history;
    private int historyCapacity;
    private static final int _defaultSize  = 10;

    public GenericScrollback() {
        this(_defaultSize);
    }
    public GenericScrollback(int capacity) {
        historyCapacity = capacity;
        this.history = new FixedList<T>(capacity);
    }

    @Override
    public void add(T item){
        if(item == null) throw new IllegalArgumentException("The Item must Contain a value.");
        if(!isValid(item)) return;
        try{
            history.add(item);
        }catch (IndexOutOfBoundsException ex){
            history.remove(0);
            add(item);
        }
    }
    public T getPrevious(int Index){
        if(Index < 0) Index = 0;
        return history.get(Index);
    }

    @Override
    public T getLast() {
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

    public List<T> getHistory(){
        return history;
    }

    private boolean isValid(T item){

        //TODO T should compare itself by implementing IComparable.
        if(!history.isEmpty()){

            if(item instanceof String ){
               if( ((String) item).equalsIgnoreCase(((String) getLast())) )
                    return false;
            }
            else if(item instanceof PokemonEncounter){
                if(((PokemonEncounter)item).equals((PokemonEncounter) getLast()))
                    return false;
            }else{
                if(item == getLast())
                    return false;
            }
        }
        if(item instanceof String){
            if(((String) item).trim().equals(""))
                return false;
        }




            return true;
    }
}
