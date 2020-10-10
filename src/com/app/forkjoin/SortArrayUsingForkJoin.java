package com.app.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class SortArrayUsingForkJoin {
	public static void main(String[] args) {
		int size = 100;
		int[] numArray = GenerateNumbers.generate(size);

		ArraySortingTask task = new ArraySortingTask(numArray, 0, size);

		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(task);
		do {
			System.out.println("Main : Thread Count : " + pool.getActiveThreadCount());
			System.out.println("Main : Thread Steal : " + pool.getStealCount());
			System.out.println("Main : Parallelism : " + pool.getParallelism());
			try {
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());

		pool.shutdown();

		if (task.isCompletedNormally()) {
			System.out.println("Task Completed Normally!!!");
		}

		for (int i = 0; i < numArray.length; i++) {
			System.out.print(numArray[i] + " ");
		}
	}

}
