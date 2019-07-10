package datastructures.worklists;

import cse332.interfaces.worklists.FIFOWorkList;
import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/FIFOWorkList.java
 * for method specifications.
 * 
 * @author Bryan Yue
 * @version 01/10/2017
 */
public class ListFIFOQueue<E> extends FIFOWorkList<E> {
	
	private Node<E> front;
	private Node<E> back;
	private int size;
    
	// Initializes null ListFIFOQueue
    public ListFIFOQueue() {
    	initialize();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void add(E work) {
    	if (!super.hasWork()) {
    		front = new Node<E>(work, null);
    		back = front;
    	} else {
    		back.next = new Node<E>(work, null);
    		back = back.next;
    	}
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
        return front.data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E next() {
    	if (!super.hasWork()) {
        	throw new NoSuchElementException();
        }
    	E frontData = front.data;
    	front = front.next;
    	if (front == null){
    		back = null;
    	}
    	size--;
    	return frontData;
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
    	initialize();
    }
    
    /**
     * Reset the data and the size to the initial state (empty).
     */
    private void initialize() {
    	this.front = null;
    	this.back = null;
        this.size = 0;
    }
    
    /**
     * Node class for linked list.
     */
	private class Node<E> {
		E data;
		Node<E> next;	
		public Node (E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
	}
}
