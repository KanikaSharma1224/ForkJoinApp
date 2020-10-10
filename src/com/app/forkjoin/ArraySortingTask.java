package com.app.forkjoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class ArraySortingTask extends RecursiveAction {
	//int size ;
	int[] numbers;
	int low;
	int high;
	
	public ArraySortingTask(int[] numbers, int low, int high) {
		//this.size = size;
		this.numbers = numbers;
		this.low = low;
		this.high = high;
	}
	
	
		
	@Override
	protected void compute() {
		if((high - low) < 10 ) {
			sort();
		}else {
			int mid = (high + low) / 2;
			ArraySortingTask task1 = new ArraySortingTask(numbers, low, mid);
			ArraySortingTask task2 = new ArraySortingTask(numbers, mid+1, high);
			invokeAll(task1, task2);
		}
		

	}
	
	private void sort() {
		Arrays.sort(numbers);
	}

}
