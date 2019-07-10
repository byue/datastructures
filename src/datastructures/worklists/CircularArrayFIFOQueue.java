package datastructures.worklists;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.FixedSizeFIFOWorkList;
import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/FixedSizeFIFOWorkList.java
 * for method specifications.
 * 
 * @author Bryan Yue
 * @version 01/10/2017
 */
public class CircularArrayFIFOQueue<E> extends FixedSizeFIFOWorkList<E> {
	
	private E[] data;
	private int size;
	private int front;
	private int back;
	
	// initializes a null CircularArrayFIFOQueue
    public CircularArrayFIFOQueue(int capacity) {
    	super(capacity);
        initialize(capacity);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void add(E work) {
    	if (super.isFull()) {
    		throw new IllegalStateException();    		
    	}
        data[back] = work;
    	back = (back + 1) % (super.capacity());
    	size++;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public E peek() {
    	if (!super.hasWork()) {
        	throw new NoSuchElementException();
        }
    	return data[front];
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public E peek(int i) {
    	if (!super.hasWork()) {
        	throw new NoSuchElementException();
        } else if (i < 0 || i >= size) {
        	throw new IndexOutOfBoundsException();
        } 
    	return data[convertQueueIndextoArrayIndex(i)];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E next() {
    	if (!super.hasWork()) {
        	throw new NoSuchElementException();
        }
        E frontData = data[front];
        front = (front + 1) % (super.capacity());
        size--;
        return frontData;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(int i, E value) {
    	if (!super.hasWork()) {
        	throw new NoSuchElementException();
        } else if (i < 0 || i >= size) {
        	throw new IndexOutOfBoundsException();
        }
        data[convertQueueIndextoArrayIndex(i)] = value;
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
        initialize(super.capacity());
    }

    @Override
    public int compareTo(FixedSizeFIFOWorkList<E> other) {
        // You will implement this method in p2. Leave this method unchanged for p1.
    	
    	
    	
        throw new NotYetImplementedException();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        // You will finish implementing this method in p2. Leave this method unchanged for p1.
        if (this == obj) {
            return true;
        }
        else if (!(obj instanceof FixedSizeFIFOWorkList<?>)) {
            return false;
        }
        else {
            FixedSizeFIFOWorkList<E> other = (FixedSizeFIFOWorkList<E>) obj;

            // Your code goes here

            throw new NotYetImplementedException();
        }
    }

    @Override
    public int hashCode() {
        // You will implement this method in p2. Leave this method unchanged for p1.
        throw new NotYetImplementedException();
    }
    
    /**
     * Converts index of CircularArrayFIFOQueue to array index.
     */
    private int convertQueueIndextoArrayIndex(int i) {
    	return (i + front) % super.capacity();
    }
    
    /**
     * Reset the data and the size to the initial state (empty).
     */
    @SuppressWarnings("unchecked")
    private void initialize(int capacity) {
        this.data = (E[]) new Comparable[capacity];
        this.size = 0;
        this.front = 0;
        this.back = 0;	
    }
}
