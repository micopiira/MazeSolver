package me.micopiira.maze;

import me.micopiira.math.Matrix;
import me.micopiira.math.Vector2;
import me.micopiira.math.algorithm.astar.AStar;
import me.micopiira.math.algorithm.astar.Node;

import java.util.*;
import java.util.stream.Collectors;

public class AStarMazeSolver implements MazeSolver {

	private final AStar aStar = new AStar();

	@Override
	public Optional<List<Vector2>> solve(Matrix<MazePoint> matrix) {
		Vector2 start = matrix.findFirst(MazePoint.START).orElseThrow(() -> new RuntimeException("No starting point found from matrix!"));
		Vector2 goal = matrix.findFirst(MazePoint.GOAL).orElseThrow(() -> new RuntimeException("No goal found from matrix!"));

		Matrix<Node> nodeMatrix = new Matrix<>(matrix.getElements().entrySet().stream()
				.map(entry -> {
					Node node = new Node(entry.getKey(), !entry.getValue().equals(MazePoint.WALL));
					return new AbstractMap.SimpleEntry<>(entry.getKey(), node);
				})
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

		return aStar.findPath(nodeMatrix, start, goal);
	}
}
