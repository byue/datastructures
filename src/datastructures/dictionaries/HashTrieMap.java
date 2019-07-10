package datastructures.dictionaries;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import cse332.interfaces.misc.BString;
import cse332.interfaces.trie.TrieMap;

/**
 * See cse332/interfaces/trie/TrieMap.java
 * and cse332/interfaces/misc/Dictionary.java
 * for method specifications.
 * 
 * @author Linxing Preston Jiang
 * @version 01/13/2017
 */
public class HashTrieMap<A extends Comparable<A>, K extends BString<A>, V> extends TrieMap<A, K, V> {
    // index needed for method reuse - 0: insert; 1: find 2: findPrefix
    private static final int[] INDICATOR = {0, 1, 2};
    // private field to take the return value for insert() and find()
    private V rtn = null;
    
	public class HashTrieNode extends TrieNode<Map<A, HashTrieNode>, HashTrieNode> {
        public HashTrieNode() {
            this(null);
        }

        public HashTrieNode(V value) {
            this.pointers = new HashMap<A, HashTrieNode>();
            this.value = value;
        }

        @Override
        public Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator() {
            return pointers.entrySet().iterator();
        }
    }

    public HashTrieMap(Class<K> KClass) {
        super(KClass);
        this.root = new HashTrieNode();
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public V insert(K key, V value) {
    	boolean noMapping = loopThrough(INDICATOR[0], key, value);
        return noMapping ? null : this.rtn;
    }

    @SuppressWarnings("unchecked")
    @Override
    public V find(K key) {
    	boolean noMapping = loopThrough(INDICATOR[1], key, null);
        return noMapping ? null : this.rtn;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean findPrefix(K key) {
        return !loopThrough(INDICATOR[2], key, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(K key) {
    	if(key == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	// handle the empty string
    	if(key.size() == 0 && this.root.value != null) {
    		this.root.value = null;
    		this.size--;
    	}
    	
    	// get the iterator for key
    	Iterator<A> keyIt = key.iterator();
		// start checking the trie from the root
        HashTrieNode node = (HashTrieMap<A, K, V>.HashTrieNode) this.root;
        
        HashTrieNode deleteNode = null;
        A savedKey = null;
        boolean metNull = false;
        
        while(keyIt.hasNext()) {
        	A temp = keyIt.next();
        	HashTrieNode nextNode = node.pointers.get(temp);
        	if(nextNode == null) {
        		return;
        	}
        	// at the second last node
        	if(!keyIt.hasNext()) {
        		// the key does not exist
        		if(nextNode.value == null) {
        			return;
        		}
        		// there is fork at the second last node
            	if(node.pointers.size() > 1) {
            		deleteNode = node;
            		savedKey = temp;
            		metNull = true;
            	}
        		// if nextNode is a leaf
        		if(nextNode.pointers.isEmpty()) {
        			// remove deleteNode if not null
        			if(deleteNode != null) {
        				deleteNode.pointers.remove(savedKey);
        			} else {
        				node.pointers.remove(temp);	
        			}
        		} 
        		nextNode.value = null;
        		this.size--;
        		return;
        	}
        	
        	// if the nextNode value is null and (there are branches OR no branch and we haven't met a null value)
        	// record this node as where the delete can start from
        	if(nextNode.value == null && 
        			((node.pointers.size() > 1) || (node.pointers.size() <= 1 && !metNull))) {
        		deleteNode = node;
        		savedKey = temp;
        		metNull = true;
        	} else if(nextNode.value != null) { // if the nextNode value is not null, clear the record for delete
    			deleteNode = null;
                savedKey = null;
                metNull = false;	
        	}
        	node = node.pointers.get(temp);
        }
    }    
    
    @Override
    public void clear() {
    	this.root = new HashTrieNode();
    	this.size = 0;
    }
    
    /**
     * A general method to loop through the Trie for insert, find, and findPrefix implementation.
     * 
     * @param indicator
     * 				   an int indicator to differentiate between {@link #insert(BString, Object)} (<code>indicator == 0</code>), {@link #find(BString)} (<code>indicator == 1</code>), and {@link #findPrefix(BString)} (<code>indicator == 2</code>)
     * @param key
     * 			 the key to look for in the Trie
     * @param value
     *             the value we want to insert (NULL for the other two method)
     * @return true if the key exists in the Trie
     */
    private boolean loopThrough(int indicator, K key, V value) {
    	
    	if(key == null || (value == null && indicator == 0)) {
    		throw new IllegalArgumentException();
    	}
    	
    	 // get the iterator for key
    	Iterator<A> keyIt = key.iterator();
		// start checking the trie from the root
        @SuppressWarnings("unchecked")
		HashTrieNode node = (HashTrieMap<A, K, V>.HashTrieNode) this.root;
        
        // keep track if the mapping for key exists
        boolean noMapping = false;
        while(keyIt.hasNext()) {
        	A temp = keyIt.next();
        	// if this node contains the alphabet, check the next node
        	if(!node.pointers.containsKey(temp)) {
        		noMapping = true;
        		// for insert
        		if(indicator == 0) {
        			node.pointers.put(temp, new HashTrieNode());	
        		} else {
        			// for find() and findPrefix();
        			break;
        		}
        	} 
        	node = node.pointers.get(temp);
        }
        this.rtn = node.value;
        
        if(indicator == 0) {
        	node.value = value;
            this.size++;
        }
        return noMapping;
    }
}
