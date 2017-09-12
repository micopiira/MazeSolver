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
	 * TODO: Optimize!!! Use {@link PriorityQueue} for openSet? Or atleast don't sort openSet list on every iteration!
	 */
	public Optional<List<Vector2>> findPath(Matrix<Node> matrix, Vector2 start, Vector2 target) {
		Node startNode = matrix.get(start).get();
		Node targetNode = matrix.get(target).get();
		startNode.setG(0);
		startNode.setH(start.manhattanDistance(target));

		HashSet<Node> closedSet = new HashSet<>();
		List<Node> openSet = new ArrayList<>();
		openSet.add(startNode);

		while (openSet.size() > 0) {
			Collections.sort(openSet);
			Node current = openSet.get(0);

			if (current.getCoordinate().equals(target))
				return Optional.of(retracePath(startNode, targetNode).stream().map(Node::getCoordinate).collect(Collectors.toList()));

			openSet.remove(current);
			closedSet.add(current);

			for (Node neighbour : matrix.getNeighbors(current.getCoordinate())) {
				if (!neighbour.isWalkable() || closedSet.contains(neighbour))
					continue;

				if (!openSet.contains(neighbour))
					openSet.add(neighbour);

				int tentativeGScore = current.getG() + 10;

				if (tentativeGScore >= neighbour.getG())
					continue;

				neighbour.setParent(current);
				neighbour.setG(tentativeGScore);
				neighbour.setH(neighbour.getCoordinate().manhattanDistance(target));
			}
		}
		return Optional.empty();
	}

}
