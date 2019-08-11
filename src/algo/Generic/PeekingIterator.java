package algo.Generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

class PeekingIterator<T> implements Iterator<T> {
    private T next = null;
    private Iterator<T> iter;
    boolean isEnd;

    public PeekingIterator(Iterator<T> iterator) {
        // initialize any member here.
        this.iter = iterator;
        if (iter.hasNext()){
            next = (T)iter.next();
            isEnd = false;
        }else{
            isEnd = true;
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public T peek() {
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public T next() {
        if(isEnd) throw  new NoSuchElementException();
        T res = next;
        if(iter.hasNext()){
            next = iter.next();
        }else{
            isEnd = true;
        }
        return res;
    }

    @Override
    public boolean hasNext() {
        return !isEnd;
    }
}