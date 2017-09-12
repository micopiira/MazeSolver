package me.micopiira.math;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Vector2 {
	private final int x;
	private final int y;

	private Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static Vector2 of(int x, int y) {
		return new Vector2(x, y);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vector2 that = (Vector2) o;
		return x == that.x && y == that.y;
	}

	public List<Vector2> getNeighbours() {
		return Arrays.asList(
				Vector2.of(this.getX() - 1, this.getY()),
				Vector2.of(this.getX(), this.getY() - 1),
				Vector2.of(this.getX(), this.getY() + 1),
				Vector2.of(this.getX() + 1, this.getY())
		);
	}

	public int manhattanDistance(Vector2 other) {
		return Math.abs(this.getX() - other.getX()) + Math.abs(this.getY() + other.getY());
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return "Vector2{" +
				"x=" + x +
				", y=" + y +
				'}';

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
