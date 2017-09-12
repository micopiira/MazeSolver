package me.micopiira.math;

import java.util.*;
import java.util.stream.Collectors;

public class Matrix<T> {

	private Map<Vector2, T> elements = new HashMap<>();

	@Override
	public String toString() {
		return "Matrix{" +
				"elements=" + elements +
				'}';
	}

	public Matrix(Map<Vector2, T> elements) {
		this.elements = elements;
	}

	public Matrix(List<List<T>> maze) {
		for (int x = 0; x < maze.size(); x++) {
			for (int y = 0; y < maze.get(x).size(); y++) {
				elements.put(Vector2.of(x, y), maze.get(x).get(y));
			}
		}
	}

	public Map<Vector2, T> getElements() {
		return elements;
	}

	public Optional<T> get(int x, int y) {
		return this.get(Vector2.of(x, y));
	}

	public Optional<T> get(Vector2 coordinate) {
		return Optional.ofNullable(elements.get(coordinate));
	}

	public void set(Vector2 coordinate, T t) {
		this.elements.put(coordinate, t);
	}

	public List<T> getNeighbors(Vector2 coordinate) {
		return coordinate.getNeighbours().stream()
				.map(this::get)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
	}

	public Optional<Vector2> findFirst(T element) {
		return elements.entrySet().stream()
				.filter(entry -> entry.getValue().equals(element))
				.map(Map.Entry::getKey)
				.findFirst();
	}
}
