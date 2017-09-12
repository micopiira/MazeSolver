package me.micopiira;

import me.micopiira.math.Matrix;
import me.micopiira.math.Vector2;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		Matrix<Integer> integerMatrix = new Matrix<>(Arrays.asList(
				Arrays.asList(1,2,3),
				Arrays.asList(4,5,6)
		));

		System.out.println(integerMatrix.getNeighbors(Vector2.of(1, 1)));

		System.out.println(integerMatrix);
	}
}
