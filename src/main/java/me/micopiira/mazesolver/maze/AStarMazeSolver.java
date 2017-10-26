package me.micopiira.mazesolver.maze;

import me.micopiira.mazesolver.math.Matrix;
import me.micopiira.mazesolver.math.Vector2;
import me.micopiira.mazesolver.math.algorithm.astar.AStar;
import me.micopiira.mazesolver.math.algorithm.astar.Node;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class AStarMazeSolver implements MazeSolver {

	private final AStar aStar = new AStar();

	private Matrix<Node> mazeToNodeMatrix(Matrix<MazePoint> maze) {
		return new Matrix<>(maze.getElements().entrySet().stream()
				.map(entry -> new Node(entry.getKey(), !entry.getValue().equals(MazePoint.WALL)))
				.collect(Collectors.toMap(Node::getCoordinate, Function.identity())));
	}

	@Override
	public Optional<List<Vector2>> solve(Matrix<MazePoint> maze) {
		Vector2 start = maze.findFirst(MazePoint.START).orElseThrow(() -> new RuntimeException("No starting point found from matrix!"));
		Vector2 goal = maze.findFirst(MazePoint.GOAL).orElseThrow(() -> new RuntimeException("No goal found from matrix!"));
		Matrix<Node> nodeMatrix = mazeToNodeMatrix(maze);
		return aStar.findPath(nodeMatrix, nodeMatrix.get(start).get(), nodeMatrix.get(goal).get());
	}
}
