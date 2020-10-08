package com.app.forkjoin;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class Task extends RecursiveAction {
	private List<Product> products;
	private int first;
	private int last;
	private double increment;

	public Task(List<Product> products, int first, int last, double increment) {
		this.products = products;
		this.first = first;
		this.last = last;
		this.increment = increment;
	}

	@Override
	protected void compute() {
		if ((last - first) < 10)

		{
			updatePrices();
		} else {
			int mid = (last + first) / 2;
			System.out.println("Task : " + " Pending Tasks : " + getQueuedTaskCount());
			Task task1 = new Task(products, first, mid + 1, increment);
			Task task2 = new Task(products, mid + 1, last, increment);
			invokeAll(task1, task2);

		}
	}

	private void updatePrices() {
		for (int i = first; i < last; i++) {
			Product p = products.get(i);
			p.setPrice(p.getPrice() * (1 + increment));
		}
	}

}
