package datastructures.worklists;

import cse332.interfaces.worklists.PriorityWorkList;

import java.util.NoSuchElementException;


/**
 * See cse332/interfaces/worklists/PriorityWorkList.java
 * for method specifications.
 * 
 * @author Linxing Preston Jiang
 * @author Bryan Yue
 * @version 01/15/2017
 */
public class MinFourHeap<E extends Comparable<E>> extends PriorityWorkList<E> {
    /* Do not change the name of this field; the tests rely on it to work correctly. */
    private E[] data;
    private int size;
    
	public MinFourHeap() {
    	reset();
    }
	
	/**
     * {@inheritDoc}
     */
    public boolean hasWork() {
        return super.hasWork();
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void add(E work) {
    	// put work in the array
    	if(size < data.length) {
        	data[size] = work;
        } else {
        	// need a bigger array
        	E[] temp = (E[]) new Comparable[data.length * 2];
	
        	// copy data over to the new bigger array
        	for(int i = 0; i < data.length; i++) {
        		temp[i] = data[i];
        	}
        	data = temp;
        	data[size] = work;
        }
    	// check if the new work violates heap property. If so, "percolate up".
    	int parent = parent(size);
    	int child = size;
    	while(data[parent].compareTo(data[child]) > 0) {
    		// swap the parent and the child
    		E temp = data[parent];
    		data[parent] = data[child];
    		data[child] = temp;
    		// update parent and child
    		child = parent;
    		parent = parent(parent);
    	}
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
    	return data[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E next() {
    	if(size == 0) {
        	throw new NoSuchElementException();
        }
        E root = data[0];
        int node = 0;
        // move the most right "leaf" element to root
        data[0] = data[size-1];
        data[size-1] = null;
        // check if the new work violates heap property. If so, "percolate down".
        int smallestChild;
        while(true) {
        	// get the smallest child of node
        	smallestChild = smallestChild(children(node));
        	// if node doesn't have children or node is smaller than all of its children
	
        	if(smallestChild == -1 || data[node].compareTo(data[smallestChild]) <= 0) {
        		break;
        	}
        	// swap node with its smallest child
        	E temp = data[node];
        	data[node] = data[smallestChild];
        	data[smallestChild] = temp;
        	node = smallestChild;
        }
        size--;
        return root;
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
     * Given an index k, return the index of its "parent" in a 4-heap.
     * 
     * @param k 
     * 		   the index of a "child" element
     * 
     * @return the index of its "parent" element in the array
     */
    private int parent(int k) {
    	return (k-1) / 4;
    }
    
    /**
     * Given an index k, return the indices of its children. IF the child is out of the array, put -1
     * 
     * @param k
     * 		   the index of a "parent" element
     * 
     * @return the indices of its "children" 
     */
    private int[] children(int k) {
    	int[] children=  new int[4];
    	for(int i = 1; i <= 4; i++) {
    		// if the index is out of range, put -1
    		children[i-1] = (4 * k + i > data.length - 1)? -1 : (4 * k + i);
    	}
    	return children;
    }
    
    /** 
     * Find the smallest "child" element in an array
     * 
     * @param children
     * 				  an array of indices of "child" elements
     * 
     * @return the index of the smallest child in the array
     */
    private int smallestChild(int[] children) {
    	int smallest;
    	if(children[0] != -1 && data[children[0]] != null) {
    		smallest = children[0];
    	} else {
    		// if the first index is -1 or all children are null, return -1
    		return -1;
    	}
    	for(int i : children) {
    		// find the smallest child
    		if(i != -1 && data[i] != null && data[i].compareTo(data[smallest]) < 0) {
    			smallest = i;
    		}
    	}
    	return smallest;
    }
    
    /**
     * Reset the data and the size to the initial state (empty).
     */
    @SuppressWarnings("unchecked")
	private void reset() {
    	this.data = (E[]) new Comparable[20];
    	this.size = 0;
    }
}
