package me.micopiira;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

	private static final MazeSolver mazeSolver = new AStarMazeSolver();

	private static void drawMaze(Maze<MazePoint> maze) {
		for (int y = 0; y < maze.getRowCount(); y++) {
			for (int x = 0; x < maze.getColumnCount(); x++) {
				System.out.print(maze.get(Coordinate.of(x, y)).get().getS());
			}
			System.out.println();
		}

	}

	public static void main(String[] args) {
		Maze<MazePoint> maze = new Maze<>(
				Arrays.asList(
						Arrays.asList(MazePoint.EMPTY, MazePoint.START, MazePoint.EMPTY, MazePoint.EMPTY),
						Arrays.asList(MazePoint.WALL, MazePoint.EMPTY, MazePoint.WALL, MazePoint.EMPTY),
						Arrays.asList(MazePoint.WALL, MazePoint.EMPTY, MazePoint.WALL, MazePoint.EMPTY),
						Arrays.asList(MazePoint.EMPTY, MazePoint.EMPTY, MazePoint.WALL, MazePoint.EMPTY),
						Arrays.asList(MazePoint.EMPTY, MazePoint.EMPTY, MazePoint.EMPTY, MazePoint.GOAL),
						Arrays.asList(MazePoint.EMPTY, MazePoint.WALL, MazePoint.WALL, MazePoint.EMPTY),
						Arrays.asList(MazePoint.WALL, MazePoint.WALL, MazePoint.WALL, MazePoint.EMPTY)
				)
		);
		System.out.println(mazeSolver.solve(maze));
		drawMaze(maze);
	}
}
