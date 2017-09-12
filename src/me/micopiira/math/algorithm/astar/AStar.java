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

	public Optional<List<Vector2>> findPath(Matrix<Node> matrix, Vector2 start, Vector2 end) {
		Node startNode = matrix.get(start).get();
		Node targetNode = matrix.get(end).get();
		PriorityQueue<Node> openSet = new PriorityQueue<>();
		HashSet<Node> closedSet = new HashSet<>();
		openSet.add(startNode);

		while (!openSet.isEmpty()) {
			Node node = openSet.poll();
			closedSet.add(node);

			if (node == targetNode) {
				return Optional.of(retracePath(startNode, targetNode).stream().map(Node::getCoordinate).collect(Collectors.toList()));
			}

			for (Node neighbour : matrix.getNeighbors(node.getCoordinate())) {
				if (!neighbour.isWalkable() || closedSet.contains(neighbour)) {
					continue;
				}
				int newCostToNeighbour = node.getG() + node.getCoordinate().manhattanDistance(neighbour.getCoordinate());
				if (newCostToNeighbour < neighbour.getG() || !openSet.contains(neighbour)) {
					neighbour.setG(newCostToNeighbour);
					neighbour.setH(neighbour.getCoordinate().manhattanDistance(targetNode.getCoordinate()));
					neighbour.setParent(node);
					if (openSet.contains(neighbour)) {
						openSet.remove(neighbour);
					}
					openSet.add(neighbour);
				}
			}
		}
		return Optional.empty();
	}
}
