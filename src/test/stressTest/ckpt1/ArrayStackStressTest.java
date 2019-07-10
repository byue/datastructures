package test.stressTest.ckpt1;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Stack;

import cse332.interfaces.worklists.WorkList;
import datastructures.worklists.ArrayStack;

/**
 * Stress test for {@link datastructures.worklists.ArrayStack} with ten million input
 * 
 * Passed!
 * 
 * @author Linxing Preston Jiang
 * @version 1/13/2017
 *
 */
public class ArrayStackStressTest {
	public static final int testNum = 10000000;
	// standard java stack 
	private static Stack<Integer> stack = new Stack<>();
	// own implementation
	private static WorkList<Integer> myStack = new ArrayStack<>();
	
	private static void addNum() {
		// random integers into array;
		Random rand = new Random();
		for(int i=0; i < testNum; i++) {
			int randNum = rand.nextInt();
			stack.push(randNum);
			myStack.add(randNum);
		}
	}
	
	private static boolean testNext() {
		addNum();
		for(int i = 0; i < testNum; i++) {
			int x = stack.pop();
			int y = myStack.next();
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
			stack.pop();
			myStack.next();
			sizeTracker--;
			if (sizeTracker != stack.size() || stack.size() != myStack.size()){
				return false;
			}
		}
		return true;
	}
	
	private static boolean testPeek() {
		addNum();
		boolean result = true;
		int sizeTracker = testNum;
		while(stack.size()!=0 && myStack.size()!=0) {
			int x = stack.peek();
			int y = myStack.peek();
			if (x != y || stack.size() != myStack.size() || myStack.size() != sizeTracker){
				return false;
			}
			stack.pop();
			myStack.next();
			sizeTracker--;
		}
		return true;
	}
	
	private static boolean testAddAndNext() {
		addNum();
		for(int i = 0; i < testNum / 2; i++) {
			int x = stack.pop();
			int y = myStack.next();
			if (x != y) {
				return false;
			}
		}
		addNum();
		for(int i = 0; i < (testNum * 3 / 2); i++) {
			int x = stack.pop();
			int y = myStack.next();
			if (x != y) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean testClear() {
		addNum();
		boolean result = true;
		stack.clear();
		myStack.clear();
		if (myStack.size() != 0 || myStack.size() != stack.size()){
			return false;
		}
		try {
			result = false;
			myStack.next();
		} catch (NoSuchElementException e) {
			result = true;
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(testNext());
		System.out.println(testSize());
		System.out.println(testPeek());
		System.out.println(testAddAndNext());
		System.out.println(testClear());
	}
}
