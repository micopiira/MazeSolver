package me.micopiira;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		Matrix<Integer> integerMatrix = new Matrix<>(Arrays.asList(
				Arrays.asList(1,2,3),
				Arrays.asList(4,5,6)
		));

		System.out.println(integerMatrix.get(Vector2.of(1, 0)));

		System.out.println(integerMatrix);
	}
}
