package org.waikato.comp204.scrollback;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by Sven on 8/10/2016.
 *
 * Uses the Scrollback class to generate a view
 *
 */
public class ScrollbackController<T> {
    private GenericScrollback<T> scrollback;

    public ScrollbackController(GenericScrollback<T> scrollback) {
        this.scrollback = scrollback;
    }

    public String getScrollBackData(){
        StringBuilder sb = new StringBuilder();
        sb.append("Scrollback capacity: " + scrollback.getCapacity());
        sb.append(" Current elements: " + scrollback.getCount());

        for (T item : scrollback.getHistory()) {
            sb.append("\n" + item.toString());
        }

        return sb.toString();
    }

    public T getPrevious(String data){
        //Determines number of "." without using regex or other library.
        int count = data.length() - data.replace(".", "").length();
        T previousItem = scrollback.getPrevious(scrollback.getCount() - count);
        scrollback.add(previousItem);
        return previousItem;
    }

    public String clear(){
        scrollback.clear();
        return "List reset.";
    }

    public String add(T item){
        scrollback.add(item);
        return "Ok.";
    }
}
