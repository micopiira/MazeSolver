package me.micopiira;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Coordinate {
	private final int x;
	private final int y;

	private Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static Coordinate of(int x, int y) {
		return new Coordinate(x, y);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Coordinate that = (Coordinate) o;
		return x == that.x &&
				y == that.y;
	}

	public List<Coordinate> getNeighbours() {
		// Diagonal neighbours commented out
		return Arrays.asList(
				// Coordinate.of(this.getX() - 1, this.getY() - 1),
				Coordinate.of(this.getX() - 1, this.getY()),
				// Coordinate.of(this.getX() - 1, this.getY() + 1),
				Coordinate.of(this.getX(), this.getY() - 1),
				Coordinate.of(this.getX(), this.getY() + 1),
				// Coordinate.of(this.getX() + 1, this.getY() - 1),
				Coordinate.of(this.getX() + 1, this.getY())
				// Coordinate.of(this.getX() + 1, this.getY() + 1)
		);
	}

	public int manhattanDistance(Coordinate other) {
		return Math.abs(this.getX() - other.getX()) + Math.abs(this.getY() + other.getY());
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return "Coordinate{" +
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
