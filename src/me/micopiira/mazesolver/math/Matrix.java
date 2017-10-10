package me.micopiira.mazesolver.math;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Matrix<T> {

	private final Map<Vector2, T> elements;

	public Matrix() {
		this(new HashMap<>());
	}

	public Matrix(Map<Vector2, T> elements) {
		this.elements = elements;
	}

	public Matrix(List<List<T>> maze) {
		this();
		for (int x = 0; x < maze.size(); x++) {
			for (int y = 0; y < maze.get(x).size(); y++) {
				elements.put(Vector2.of(x, y), maze.get(x).get(y));
			}
		}
	}

	public Matrix(T t, int size) {
		this();
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				this.set(Vector2.of(x, y), t);
			}
		}
	}

	@Override
	public String toString() {
		return "Matrix{" +
				"elements=" + elements +
				'}';
	}

	public Map<Vector2, T> getElements() {
		return elements;
	}

	public Optional<T> get(Vector2 coordinate) {
		return Optional.ofNullable(elements.get(coordinate));
	}

	public void set(Vector2 coordinate, T t) {
		this.elements.put(coordinate, t);
	}

	public List<T> getNeighbours(Vector2 coordinate) {
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
