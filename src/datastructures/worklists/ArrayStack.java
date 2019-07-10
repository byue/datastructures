package datastructures.worklists;

import java.util.NoSuchElementException;

import cse332.interfaces.worklists.LIFOWorkList;

/**
 * See cse332/interfaces/worklists/LIFOWorkList.java
 * for method specifications.
 * 
 * @author Linxing Preston Jiang
 * @version 01/07/2017
 */
public class ArrayStack<E> extends LIFOWorkList<E> {
	
	private E[] data;
	private int size;
	
	public ArrayStack() {
		reset();
    }

	/**
     * {@inheritDoc}
     */
    @Override
    public void add(E work) {
        if(size >= data.length) {
	    	// need a bigger array
	    	@SuppressWarnings("unchecked")
			E[] temp = (E[]) new Object[data.length * 2];
	    	// copy data over to the new bigger array
	    	for(int i = 0; i < data.length; i++) {
	    		temp[i] = data[i];
	    	}
	    	data = temp;
        }
        data[size] = work;
        size++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E peek() {
        if(!hasWork()) {
        	throw new NoSuchElementException();
        }
    	return data[size-1];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E next() {
    	if(!hasWork()) {
        	throw new NoSuchElementException();
        }
    	// remove from the end of the array
        E e = data[size-1];
        size--;
        if(size < data.length / 4 && data.length /2 >= 10) {
	    	// need a smaller array
	    	@SuppressWarnings("unchecked")
			E[] temp = (E[]) new Object[data.length / 2];
	    	// copy data over to the new smaller array
	    	for(int i = 0; i < size; i++) {
	    		temp[i] = data[i];
	    	}
	    	data = temp;
        }
        return e;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
	@Override
    public void clear() {
        reset();
    }
    
    /**
     * Reset the data and the size to the initial state (empty).
     */
    @SuppressWarnings("unchecked")
	private void reset() {
    	this.data = (E[]) new Object[10];
    	this.size = 0;
    }
}
