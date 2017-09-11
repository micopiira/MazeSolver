package me.micopiira;

import java.util.*;
import java.util.stream.Collectors;

public class AStarMazeSolver implements MazeSolver {

	private class Node {
		private int H;
		private int G;
		private Node parent;
		private boolean walkable;
		private Coordinate coordinate;

		private Node(Coordinate coordinate, boolean walkable) {
			this.coordinate = coordinate;
			this.walkable = walkable;
		}

		public int getF() {
			return H + G;
		}

		public int getH() {
			return H;
		}

		public void setH(int h) {
			H = h;
		}

		public int getG() {
			return G;
		}

		public void setG(int g) {
			G = g;
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public boolean isWalkable() {
			return walkable;
		}

		public void setWalkable(boolean walkable) {
			this.walkable = walkable;
		}

		public Coordinate getCoordinate() {
			return coordinate;
		}

		public void setCoordinate(Coordinate coordinate) {
			this.coordinate = coordinate;
		}
	}

	private List<Node> retracePath(Node startNode, Node endNode) {
		Deque<Node> path = new ArrayDeque<>();
		Node currentNode = endNode;
		while (currentNode != startNode) {
			path.addFirst(currentNode);
			currentNode = currentNode.getParent();
		}
		return new ArrayList<>(path);
	}

	public Optional<List<Coordinate>> findPath(Matrix<Node> matrix, Coordinate start, Coordinate end) {
		Node startNode = matrix.get(start).get();
		Node targetNode = matrix.get(end).get();

		List<Node> openSet = new ArrayList<>();
		HashSet<Node> closedSet = new HashSet<>();

		openSet.add(startNode);
		while (openSet.size() > 0) {
			Node node = openSet.get(0);
			for (int i = 1; i < openSet.size(); i ++) {
				if (openSet.get(i).getF() < node.getF() || openSet.get(i).getF() == node.getF()) {
					if (openSet.get(i).getH() < node.getH())
						node = openSet.get(i);
				}
			}

			openSet.remove(node);
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

					if (!openSet.contains(neighbour))
						openSet.add(neighbour);
				}
			}
		}
		return Optional.empty();
	}

	@Override
	public Optional<List<Coordinate>> solve(Matrix<MazePoint> matrix) {
		Coordinate start = matrix.findFirst(MazePoint.START).orElseThrow(() -> new RuntimeException("No starting point found from matrix!"));
		Coordinate goal = matrix.findFirst(MazePoint.GOAL).orElseThrow(() -> new RuntimeException("No goal found from matrix!"));

		Matrix<Node> nodeMatrix = new Matrix<>(matrix.getMazePoints().entrySet().stream()
				.map(entry -> {
					Node node = new Node(entry.getKey(), !entry.getValue().equals(MazePoint.WALL));
					return new AbstractMap.SimpleEntry<>(entry.getKey(), node);
				})
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

		return findPath(nodeMatrix, start, goal);
	}
}
