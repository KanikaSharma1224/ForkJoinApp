package com.app.forkjoin;

import java.util.Random;

public class GenerateNumbers {
	public static int[] generate(int size) {
		int[] nums = new int[size];
		Random random = new Random();
		for (int i = 0; i < nums.length; i++) {
			nums[i] = random.nextInt(size);
		}
		return nums;
	}

}
