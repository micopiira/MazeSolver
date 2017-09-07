package me.micopiira;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Maze<T> {
	private Map<Coordinate, T> mazePoints = new HashMap<>();

	public Maze(Map<Coordinate, T> mazePoints) {
		this.mazePoints = mazePoints;
	}

	public Maze(List<List<T>> maze) {
		for (int y = 0; y < maze.size(); y++) {
			for (int x = 0; x < maze.get(y).size(); x++) {
				mazePoints.put(Coordinate.of(x, y), maze.get(y).get(x));
			}
		}
	}

	public int getRowCount() {
		//noinspection ConstantConditions
		return mazePoints.keySet().stream().mapToInt(Coordinate::getY).max().getAsInt() + 1;
	}

	public int getColumnCount() {
		//noinspection ConstantConditions
		return mazePoints.keySet().stream().mapToInt(Coordinate::getX).max().getAsInt() + 1;
	}

	public Map<Coordinate, T> getMazePoints() {
		return mazePoints;
	}

	public Optional<T> get(int x, int y) {
		return this.get(Coordinate.of(x, y));
	}

	public Optional<T> get(Coordinate coordinate) {
		return Optional.ofNullable(mazePoints.get(coordinate));
	}

	public void set(Coordinate coordinate, T t) {
		this.mazePoints.put(coordinate, t);
	}

	public List<T> getNeighbors(Coordinate coordinate) {
		return coordinate.getNeighbours().stream()
				.map(this::get)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
	}

	public Optional<Coordinate> findFirst(T mazePoint) {
		return mazePoints.entrySet().stream()
				.filter(entry -> entry.getValue().equals(mazePoint))
				.map(Map.Entry::getKey)
				.findFirst();
	}
}
