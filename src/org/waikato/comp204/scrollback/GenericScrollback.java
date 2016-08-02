package org.waikato.comp204.scrollback;

/**
 * A Generic Scrollback implementation
 */
public class GenericScrollback<T> implements GenericScrollbackInterface<T> {
    @Override
    public void add(T item) {

    }

    @Override
    public T getLast() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
