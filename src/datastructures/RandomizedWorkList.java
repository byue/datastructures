package datastructures;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Random;
import cse332.interfaces.worklists.WorkList;

/**
 * See cse332/interfaces/worklists/WorkList.java
 * for method specifications.
 * 
 * @author Bryan Yue
 * @version 01/17/2017
 */
public class RandomizedWorkList<E> extends WorkList<E> {
	
	private final int capacity;
	private E[] data;
	private int size;
	private int ith;
	private boolean calledNext;
	// stores value from peek
	private int selectedIndex;
	// contains indices of null elements
	private HashSet<Integer> indices;
	
    public RandomizedWorkList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity should be positive");
        }
        this.capacity = capacity;
        clear();
    }
    
    /**
     * If worklist isn’t full, add work to the end.
     * Otherwise, choose a random number, j, from 0 to i. 
     * If j is a valid index in the buffer, 
     * replace the item at that index with work.
     * 
     * {@inheritDoc}
     * 
     *  @precondition next() has not been called
     *  @throws NoSuchElementException
     *             if next() has been called 
     */
    @Override
    public void add(E work) {
    	if (calledNext) {
    		throw new IllegalStateException();  
    	}
    	if (size < capacity) {
    		data[size] = work;
    		indices.remove(size);
    		size++;
    	} else {
        	Random rand = new Random();
        	int index = rand.nextInt(ith) + 1;
        	if (index < capacity) {
        		indices.remove(index);
        		data[index] = work;
        	}
    	}
    	ith++;
    }
    
    /**
     * {@inheritDoc}
     * @return a random element of the worklist
     */
    @Override
    public E peek() {
    	if (!super.hasWork()) {
        	throw new NoSuchElementException();
        }
    	if (selectedIndex != -1) {
    		return data[selectedIndex];	
    	}
    	Random rand = new Random();
    	int index = rand.nextInt(capacity);
    	while (indices.contains(index)) {
    		index = rand.nextInt(capacity);
    	}
    	selectedIndex = index;
    	return data[selectedIndex];
    }

    /**
     * {@inheritDoc}
     * @postcondition after(calledNext == true)
     * @return a random element of the worklist
     */
    @Override
    public E next() {
    	if (!super.hasWork()) {
        	throw new NoSuchElementException();
        }
    	this.calledNext = true;
    	if (selectedIndex != -1) {
    		int returnIndex = selectedIndex;
    		selectedIndex = -1;
    		size--;
    		return data[returnIndex];	
    	}
    	Random rand = new Random();
    	int index = rand.nextInt(capacity);
    	while (indices.contains(index)) {
    		index = rand.nextInt(capacity);
    	}
    	size--;
    	return data[index];
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
    @SuppressWarnings("unchecked")
    public void clear() {
    	this.calledNext = false;
    	this.size = 0;
        this.data = (E[]) new Object[capacity];
        this.ith = 0;
        this.selectedIndex = -1;
        this.indices = new HashSet<Integer>();
        for (int i = 0; i < capacity; i++){
        	indices.add(i);
        }
    }
}