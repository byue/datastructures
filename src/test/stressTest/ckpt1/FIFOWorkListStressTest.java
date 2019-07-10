package test.stressTest.ckpt1;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;

import cse332.interfaces.worklists.WorkList;
import datastructures.worklists.ListFIFOQueue;

/**
 * Stress test for {@link datastructures.worklists.FIFOWorkList}
 * 
 * Input of 9,000,000 works
 * javaQueue works with 100,000,000
 * myQueue works with 1,000,000
 * 10,000,000 causes gc overhead limit 
 * @author Bryan Yue
 * @version 1/16/2017
 *
 */
public class FIFOWorkListStressTest {
	public static final int testNum = 8000000;
	// standard java queue 
	private static Queue<Integer> javaQueue = new LinkedList<Integer>();
	// own implementation
	private static WorkList<Integer> myQueue = new ListFIFOQueue<>();
	
	private static void addNum() {
		// random integers into array;
		Random rand = new Random();
		for(int i=0; i < testNum; i++) {
			int randNum = rand.nextInt();
			javaQueue.add(randNum);
			myQueue.add(randNum);
		}
	}
	
	private static boolean testNext() {
		addNum();
		for(int i = 0; i < testNum; i++) {
			int x = javaQueue.remove();
			int y = myQueue.next();
			if (x != y) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean testSize() {
		addNum();
		int sizeTracker = testNum;
		for(int i = 0; i < testNum; i++) {
			javaQueue.remove();
			myQueue.next();
			sizeTracker--;
			if (sizeTracker != javaQueue.size() || javaQueue.size() != myQueue.size()) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean testPeek() {
		addNum();
		boolean result = true;
		int sizeTracker = testNum;
		while(javaQueue.size()!= 0 && myQueue.size() != 0) {
			int x = javaQueue.peek();
			int y = myQueue.peek();
			if (x != y || javaQueue.size() != myQueue.size() || myQueue.size() != sizeTracker) {
				return false;
			}
			javaQueue.remove();
			myQueue.next();
			sizeTracker--;
		}
		return result;
	}
	
	private static boolean testAddAndNext() {
		addNum();
		for(int i = 0; i < testNum / 2; i++) {
			int x = javaQueue.remove();
			int y = myQueue.next();
			if (x != y){
				return false;
			}
		}
		addNum();
		for(int i = 0; i < (testNum * 3/2); i++) {
			int x = javaQueue.remove();
			int y = myQueue.next();
			if (x != y){
				return false;
			}
		}
		return true;
	}
	
	private static boolean testClear() {
		addNum();
		boolean result = true;
		javaQueue.clear();
		myQueue.clear();
		if (myQueue.size() != 0 || javaQueue.size() != myQueue.size()) {
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