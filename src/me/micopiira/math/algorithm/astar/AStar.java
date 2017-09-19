package me.micopiira.math.algorithm.astar;

import me.micopiira.math.Matrix;
import me.micopiira.math.Vector2;

import java.util.*;
import java.util.stream.Collectors;

public class AStar {

	private List<Node> retracePath(Node startNode, Node endNode) {
		Deque<Node> path = new ArrayDeque<>();
		Node currentNode = endNode;
		while (currentNode != startNode) {
			path.addFirst(currentNode);
			currentNode = currentNode.getParent();
		}
		return new ArrayList<>(path);
	}

	/**
	 * Implemented using Wikipedia <a href="https://en.wikipedia.org/wiki/A*_search_algorithm">pseudocode</a>
	 */
	public Optional<List<Vector2>> findPath(Matrix<Node> matrix, Node startNode, Node targetNode) {
		startNode.setG(0);
		startNode.setH(startNode.getCoordinate().manhattanDistance(targetNode.getCoordinate()));

		HashSet<Node> closedSet = new HashSet<>();
		Queue<Node> openSet = new PriorityQueue<>();
		openSet.add(startNode);

		while (openSet.size() > 0) {
			Node current = openSet.poll();

			if (current.getCoordinate().equals(targetNode.getCoordinate()))
				return Optional.of(retracePath(startNode, targetNode).stream()
						.map(Node::getCoordinate)
						.collect(Collectors.toList()));

			openSet.remove(current);
			closedSet.add(current);

			for (Node neighbour : matrix.getNeighbours(current.getCoordinate())) {
				if (!neighbour.isWalkable() || closedSet.contains(neighbour))
					continue;

				if (!openSet.contains(neighbour))
					openSet.add(neighbour);

				int tentativeGScore = current.getG() + 10;

				if (tentativeGScore >= neighbour.getG())
					continue;

				neighbour.setParent(current);
				neighbour.setG(tentativeGScore);
				neighbour.setH(neighbour.getCoordinate().manhattanDistance(targetNode.getCoordinate()));
				openSet.remove(neighbour);
				openSet.add(neighbour);
			}
		}
		return Optional.empty();
	}

}
