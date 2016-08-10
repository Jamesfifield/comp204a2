package org.waikato.comp204.scrollback;

/**
 * A Generic Scrollback implementation
 */
public class GenericScrollback<T> implements GenericScrollbackInterface<T> {
    private FixedList<T> history;
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

    private boolean isValid(T item){

        //TODO T should compare itself.
        if(!history.isEmpty()){

            if(item instanceof String ){
               if( ((String) item).equalsIgnoreCase(((String) getLast())) )
                    return false;
            }else{
                if(item == getLast())
                    return false;
            }
        }else if(item instanceof String){
            if(((String) item).trim().equals(""))
                return false;
        }


        return true;
    }
}
