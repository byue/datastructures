package test.stressTest.ckpt1;

import java.util.NoSuchElementException;
import java.util.Random;
import cse332.interfaces.worklists.WorkList;
import cse332.interfaces.worklists.FixedSizeFIFOWorkList;
import datastructures.worklists.CircularArrayFIFOQueue;

/**
 * Stress test for {@link datastructures.worklists.CircularArrayFIFOQueue}
 * works with 1,000,000
 * @author Bryan Yue
 * @version 1/16/2017
 *
 */
public class CircularArrayFIFOQueueStressTest {
	public static final int testNum = 1000000;
	// own implementation
	private static FixedSizeFIFOWorkList<Integer> myQueue = new CircularArrayFIFOQueue<>(10*testNum);
	
	private static void addNum() {
		// random integers into array;
		Random rand = new Random();
		for(int i=0; i < testNum; i++) {
			int randNum = rand.nextInt();
			myQueue.add(randNum);
		}
	}
	
	private static boolean testNext() {
		addNum();
		for(int i = 0; i < testNum; i++) {
			int y = myQueue.next();
		}
		return true;
	}
	
	private static boolean testSize() {
		addNum();
		int sizeTracker = testNum;
		for(int i = 0; i < testNum; i++) {
			myQueue.next();
			sizeTracker--;
			if (sizeTracker != myQueue.size()) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean testPeek() {
		addNum();
		boolean result = true;
		int sizeTracker = testNum;
		while(myQueue.size() != 0) {
			int y = myQueue.peek();
			myQueue.next();
			sizeTracker--;
		}
		return result;
	}
	
	private static boolean testAddAndNext() {
		addNum();
		for(int i = 0; i < testNum / 2; i++) {
			int y = myQueue.next();
		}
		addNum();
		for(int i = 0; i < (testNum * 3/2); i++) {
			int y = myQueue.next();
		}
		return true;
	}
	
	private static boolean testClear() {
		addNum();
		boolean result = true;
		myQueue.clear();
		if (myQueue.size() != 0) {
			return false;
		}
		try {
			result = false;
			myQueue.next();
		} catch (NoSuchElementException e) {
			result = true;
		}
		return result; 
	}
	
	public static void main(String[] args) {
		System.out.println("Testing with size: " + testNum);
		System.out.println("testNext: " + testNext());
		System.out.println("testSize: " + testSize());
		System.out.println("testPeak: " + testPeek());
		System.out.println("testAddAndNext: " + testAddAndNext());
		System.out.println("testClear: " + testClear());
	}
}