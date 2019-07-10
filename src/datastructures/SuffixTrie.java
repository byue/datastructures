package datastructures;

import java.util.Iterator;

import cse332.interfaces.worklists.FIFOWorkList;
import cse332.interfaces.worklists.FixedSizeFIFOWorkList;
import cse332.types.ByteString;
import datastructures.dictionaries.HashTrieMap;
import datastructures.worklists.CircularArrayFIFOQueue;
import datastructures.worklists.ListFIFOQueue;

/**
 * SuffixTrie implementation for zip
 * 
 * @author Adam Blank, Linxing Preston Jiang
 * @version 01/12/2017
 */
public class SuffixTrie extends HashTrieMap<Byte, ByteString, Boolean> {
    // for leaves
	protected static final Byte TERMINATOR = null;
    
    // Bytes currently matched
    private FixedSizeFIFOWorkList<Byte> currentMatch;
    // all the leaves in the SuffixTrie
    private FIFOWorkList<HashTrieNode> leaves;
    // the last matched node where the match ended
    private HashTrieNode lastMatchedNode;
    // the internal buffer of the SuffixTrie 
    private FixedSizeFIFOWorkList<Byte> contents;
    
    // should not be changed after initialization
    private final int contentsSize;
    private final int maxMatchLength;

    /**
     * A new SuffixTrie is constructed with an internal buffer of size size and a
     * maximum allowed match length of maxMatchLength.
     *
     * @throws IllegalArgumentException if maxMatchLength < 0
     * @param size the size of the internal contents of the trie
     * @param maxMatchLength the maximum allowed match length
     */
    public SuffixTrie(int size, int maxMatchLength) {
    	super(ByteString.class);

    	if(maxMatchLength < 0) {
        	throw new IllegalArgumentException();
        }

    	this.contentsSize = size;
    	this.maxMatchLength = maxMatchLength;
    	
    	// initialization
    	reset(size, maxMatchLength);
    }

    /**
     * Finds the longest matching suffix in the trie for a prefix of buffer.
     * To do this, we gradually shift elements from buffer to match as we 
     * determine that they are actually a match to a suffix in the trie.
     * 
     * @postcondition currentMatch == suffix for the longest possible _partial_
     *                suffix in the trie
     * @postcondition the node representing the last matched character in the trie
     *                is stored in this.lastMatchedNode (we might need this later)
     *
     * Note that this method is not guaranteed to find a complete match -- it may,
     * in some cases, make a partial match.
     *
     * We will find a COMPLETE match when we use the buffer to traverse the tree
     * from the root to any leaf. It indicates that the next segment of the buffer
     * is exactly one of the suffixes in the trie.
     *
     * We will find a PARTIAL match when we use the buffer to traverse the tree from
     * the root, but do NOT reach a leaf.  For example, if...
     *     buffer = ['a', 'b', 'c']
     *     trie = {"abcde", "bcde", "cde", "de", "e"}
     * Then, the longest match is "abc", but this isn't a complete word in the trie.
     * There is definitely a match; it's just a partial one.
     *
     * If you find a COMPLETE match, you should return the total number of bytes you've
     * matched against the buffer. Otherwise, you should return zero.
     *
     * When implementing this method, you should start by resetting this.lastMatchedNode,
     * then start traversing from the root of the trie to try finding the new match. You
     * should not traverse starting from the old value of this.lastMatchedNode.  Make sure
     * to update this.lastMatchedNode after finishing traversing.
     * 
     * @param buffer the buffer to search with 
     * @return the total number of bytes matched
     */
    @SuppressWarnings("unchecked")
	public int startNewMatch(FIFOWorkList<Byte> buffer) {
    	// if the currentMatch is already full, return 0
    	if(currentMatch.isFull()) {
    		return 0;
    	}
    	// reset lastMatchedNode and currentMatch
    	this.lastMatchedNode = (HashTrieMap<Byte, ByteString, Boolean>.HashTrieNode) this.root;

    	int length = 0;
    	// loop through the buffer (also stops when currentMatch is full)
        while(buffer.hasWork() && !currentMatch.isFull()) {
        	Byte temp = null;
        	// if this is a match
        	if(this.lastMatchedNode.pointers.containsKey(buffer.peek())) {
        		temp = buffer.next();
        		currentMatch.add(temp);
        		this.lastMatchedNode = this.lastMatchedNode.pointers.get(temp);
        		length++;
            } else {
            	// match ends
            	break;
        	}
        }
        // if the value of the node is true, then it is a leaf, return length
        return (this.lastMatchedNode.value) ? length : 0;
    }

    /**
     * Extends this.currentMatch to handle duplicates.
     *
     * Consider the case where the buffer is:
     *     abcabcabcd
     * A good decomposition of this buffer would be:
     *     abc abc abc d
     * LZ77 can capture this idea by using *the match itself* as part of the match.
     * On the first match, we will get just 'a'. Then, just 'b'.  Then, just 'c'.
     * Now, our suffix trie is populated with "abc", "bc", and "c".
     * When we next try to match, we clearly can find "abc":
     *     abc|abcabcd
     *         ***
     *         ^--^
     *
     * The interesting idea is that the match can *continue*.  Because the next
     * character in the buffer ('a') matches the next character in the already
     * consumed window ('a'), we can continue the match.  In fact, this can
     * continue indefinitely.
     *     abc|abcabcd
     *         ***
     *         ^--^
     *          ^--^
     *           ^--^
     *     abc|abcabcd
     *            ^--x
     *
     * Eventually, it will stop matching (see above at the 'd').  Then, we output the
     * entire match.
     *
     * This special case of the LZ77 algorithm interestingly is where much of the
     * compression comes from.
     *
     * @param buffer the buffer to search against
     * @return the total number of bytes matched
     */
    public int extendMatch(FIFOWorkList<Byte> buffer) {
        // Note: this method has been provided for you. You should not make any
        // changes to this method.
        int numMatches = 0;
        while (buffer.hasWork() && 
               this.currentMatch.size() < this.currentMatch.capacity() - 1 && 
               this.currentMatch.peek(numMatches) == buffer.peek()) {
            this.currentMatch.add(buffer.next());
            numMatches += 1;
        }
        return numMatches;
    }

    /**
     * Adds the given byte to this.currentMatch. This method should
     * NOT change this.lastMatchedNode.
     *
     * If the client tries adding a byte after this.currentMatch is full,
     * you should do nothing.
     *
     * @param b the byte to add
     */
    public void addToMatch(byte b) {
        // if currentMatch is full, do nothing
    	if(currentMatch.isFull()) {
        	return;
        }
    	currentMatch.add(b);
    }

    /**
     * Returns a worklist representing the current match.  Clients of this tree
     * SHOULD NOT be able to modify this.currentMatch by modifying the returned
     * worklist.  So, this method should return a deep copy.
     *
     * @return a copy of the current match
     */
    public FIFOWorkList<Byte> getMatch() {
    	// deep copy
        FIFOWorkList<Byte> rtn = new CircularArrayFIFOQueue<>(this.currentMatch.capacity());
        Iterator<Byte> currentMatchIt = currentMatch.iterator();
        // copy the data over
        while(currentMatchIt.hasNext()) {
        	rtn.add(currentMatchIt.next());
        }
        return rtn;
    }

    /**
     * Returns the distance from the end of the current match to some leaf
     *
     * @return the number of (non-terminator) characters between lastMatchedNode and the
     *         closest leaf on an arbitrary path
     */
    public int getDistanceToLeaf() {
    	HashTrieNode temp = this.lastMatchedNode;
    	int distance = 0;
    	while(true) {
    		// the node is a leaf
    		if(temp.pointers.containsKey(TERMINATOR)) {
    			return distance;
    		}
    		// pick the first child
    		temp = temp.pointers.values().iterator().next();
    		distance++;
    	}
    }

    /**
     * Advances this trie by the found match.
     *
     * For every byte b in match, you should do the following:
     *
     * 1. If the contents of the suffixtrie are at full capacity,
     *    shift off a byte and remove the whole word from the trie
     * 2. Append b to the end of every stored node
     * 3. Re-insert the empty string back into the trie
     *
     * HINT: be sure to pay careful attention to how exactly you are updating
     * your various fields, and how exactly they interact with one another. See the
     * example and descriptions in the spec for more details about this method.
     */
    public void advance() {
        // for every byte b in match
    	while(this.currentMatch.hasWork()) {
    		Byte b = this.currentMatch.next();
    		//1.
    		if(this.contents.isFull()) {
    			// remove the whole word from the trie
    			this.delete(listToArray(this.contents));
    			// shift off a byte
    			this.contents.next();
    		}
    		//2.
    		// keep track of new leaf nodes
    		FIFOWorkList<HashTrieNode> newLeaves = new ListFIFOQueue<>();
    		// go through every current node
    		while(this.leaves.hasWork()) {
    			HashTrieNode temp = this.leaves.next();
    			temp.pointers.remove(TERMINATOR);
    			// the new leaf to be appended
    			HashTrieNode newLeaf = new HashTrieNode(true);
    			newLeaf.pointers.put(TERMINATOR, new HashTrieNode());
    			temp.pointers.put(b, newLeaf);
    			// add the newLeaf to the new leaf list
    			newLeaves.add(newLeaf);
    		}
    		//3. 
    		@SuppressWarnings("unchecked")
			HashTrieNode node = (HashTrieMap<Byte, ByteString, Boolean>.HashTrieNode) this.root;
    		node.pointers.put(TERMINATOR, new HashTrieNode());
    		
    		// update the leaf list
    		this.leaves = newLeaves;
    	}
    }
    
    /**
     * Given a <code>FixedSizeFIFOWorkList<Byte></code>, convert it into a <code>Byte</code> array.
     * 
     * @param list
     * 			  a <code>FixedSizeFIFOWorkList<Byte></code> object
     * 
     * @return an array containing the same elements
     */
    private Byte[] listToArray(FixedSizeFIFOWorkList<Byte> list) {
    	Byte[] array = new Byte[list.size()];
    	int idx = 0;
    	while(list.hasWork()) {
    		array[idx] = list.next();
    		idx++;
    	}
    	return array;
    }

    /**
     * Clears the state of this trie to identical to initialization.
     */
    @Override
    public void clear() {
        reset(this.contentsSize, this.maxMatchLength);
    }
    
    /**
     * Initialize the fields.
     * 
     * @param size
     * 			 the capacity for <code>contents</code>
     * @param maxMatchLength
     * 						the capacity for <code>currentMatch</code> 
     */
    private void reset(int size, int maxMatchLength) {
    	this.currentMatch = new CircularArrayFIFOQueue<>(maxMatchLength);
    	this.contents = new CircularArrayFIFOQueue<>(size);
    	this.leaves = new ListFIFOQueue<HashTrieNode>();
    	
    	// as a SuffixTrie, the root is always a leaf (empty string)
    	@SuppressWarnings("unchecked")
		HashTrieNode node = (HashTrieMap<Byte, ByteString, Boolean>.HashTrieNode) this.root;
    	this.lastMatchedNode = node;
    	
    	node.value = true;
    	node.pointers.put(TERMINATOR, new HashTrieNode());
    	// root shouldn't be a leaf
    }
}
