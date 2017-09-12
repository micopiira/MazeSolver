package me.micopiira.maze;

import me.micopiira.math.Matrix;
import me.micopiira.math.Vector2;
import me.micopiira.math.algorithm.astar.AStar;
import me.micopiira.math.algorithm.astar.Node;

import java.util.*;
import java.util.stream.Collectors;

public class AStarMazeSolver implements MazeSolver {

	private final AStar aStar = new AStar();

	private Matrix<Node> mazeToNodeMatrix(Matrix<MazePoint> maze) {
		return new Matrix<>(maze.getElements().entrySet().stream()
				.map(entry ->
					new AbstractMap.SimpleEntry<>(
							entry.getKey(),
							new Node(entry.getKey(), !entry.getValue().equals(MazePoint.WALL))
					)
				)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
	}

	@Override
	public Optional<List<Vector2>> solve(Matrix<MazePoint> maze) {
		Vector2 start = maze.findFirst(MazePoint.START).orElseThrow(() -> new RuntimeException("No starting point found from matrix!"));
		Vector2 goal = maze.findFirst(MazePoint.GOAL).orElseThrow(() -> new RuntimeException("No goal found from matrix!"));
		return aStar.findPath(mazeToNodeMatrix(maze), start, goal);
	}
}
