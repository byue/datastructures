package test.stressTest.ckpt1;

import java.util.NoSuchElementException;
import java.util.Random;
import datastructures.worklists.MinFourHeap;
import cse332.interfaces.worklists.PriorityWorkList;

/**
 * Stress test for {@link datastructures.worklists.MinFourHeap}
 * 
 * @author Bryan
 * @version 1/13/2017
 *
 */
public class MinFourHeapStressTest {
	public static final int testNum = 100000;
	// own implementation
	private static MinFourHeap<Integer> myHeap = new MinFourHeap<Integer>();
	
	private static void addNum() {
		Random rand = new Random();
		for(int i=0; i < testNum; i++) {
			int randNum = rand.nextInt(100000) + 1;
			myHeap.add(randNum);
		}
	}
	
	private static boolean testNext() {
		addNum();
		int previous = -1;
		for(int i = 1; i < testNum; i++) {
			int curr = myHeap.next();
			if (curr < previous) {
				System.out.println("curr: " + curr);
				System.out.println("previous : " + previous);
				System.out.println("index: " + i);
				return false;
			}
			previous = curr;
		}
		return true;
	}
	
	private static boolean testSize() {
		myHeap.clear();
		addNum();
		int sizeTracker = testNum;
		for(int i = 0; i < testNum; i++) {
			myHeap.next();
			sizeTracker--;
			if (sizeTracker != myHeap.size()) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean testPeek() {
		addNum();
		while(myHeap.size() != 0) {
			int y = myHeap.peek();
			int x = myHeap.next();
			if (x != y) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean testAddAndNext() {
		addNum();
		for(int i = 0; i < testNum / 2; i++) {
			myHeap.next();
		}
		addNum();
		for(int i = 0; i < (testNum * 3/2); i++) {
			myHeap.next();
		}
		return true;
	}
	
	private static boolean testClear() {
		addNum();
		boolean result = true;
		myHeap.clear();
		if (myHeap.size() != 0) {
			return false;
		}
		try {
			result = false;
			myHeap.next();
		} catch (NoSuchElementException e) {
			result = true;
		}
		return result; 
	}
	
	public static void main(String[] args) {
		System.out.println("Testing with size: " + testNum);
		System.out.println("testNext: " + testNext());
		//System.out.println("testSize: " + testSize());
		//System.out.println("testPeak: " + testPeek());
		//System.out.println("testAddAndNext: " + testAddAndNext());
		//System.out.println("testClear: " + testClear());
	}
}
