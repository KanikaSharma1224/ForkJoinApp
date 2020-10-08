package com.app.forkjoin;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class ForkJoinAppDriver {
	public static void main(String[] args) {
		List<Product> products = ProductListGenerator.generate(1000);
		Task taskforupdation = new Task(products, 0, products.size(), 0.20);
		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(taskforupdation);

		do {
			System.out.println("Main : Thread Count : " + pool.getActiveThreadCount());
			System.out.println("Main : Thread Steal : " + pool.getStealCount());
			System.out.println("Main : Parallelism : " + pool.getParallelism());
			try {
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!taskforupdation.isDone());

		pool.shutdown();

		if (taskforupdation.isCompletedNormally()) {
			System.out.println("Main : The task has completed normally");
		}

		for (int i = 0; i < products.size(); i++) {
			Product p = products.get(i);
			if (p.getPrice() != 12) {

				System.out.println("Product : " + p.getName() + " " + p.getPrice());
			}
		}

		System.out.println("Main : End of Program");

	}

}
