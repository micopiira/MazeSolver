package me.micopiira.gui;

import me.micopiira.math.Matrix;
import me.micopiira.math.Vector2;
import me.micopiira.maze.AStarMazeSolver;
import me.micopiira.maze.MazePoint;
import me.micopiira.maze.MazeSolver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test {
	private final MazeSolver mazeSolver;
	private Matrix<MazePoint> matrix = new Matrix<>(new HashMap<>());
	private List<Vector2> solvedPath = new ArrayList<>();
	private Matrix<MazeButton> matrixButtons = new Matrix<>(new HashMap<>());

	public Test(MazeSolver mazeSolver) {
		this.mazeSolver = mazeSolver;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Test(new AStarMazeSolver()).createAndShowGUI());
	}

	private void initMaze(int cols, int rows) {
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < cols; y++) {
				matrix.set(Vector2.of(x, y), MazePoint.EMPTY);
			}
		}
		matrix.set(Vector2.of(1, 0), MazePoint.START);
		matrix.set(Vector2.of(cols - 1, rows - 1), MazePoint.GOAL);
	}

	public void createAndShowGUI() {
		JPanel jPanel = new JPanel();
		JFrame frame = new JFrame("Test");
		frame.setContentPane(jPanel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		jPanel.setLayout(new GridLayout(2, 0));
		jPanel.setBackground(Color.BLACK);

		JPanel controls = new JPanel();
		JButton solveButton = new JButton("Solve");
		JTextField gridSize = new JTextField();
		solveButton.addActionListener(e -> {
			this.setSolvedPath(mazeSolver.solve(matrix).orElseThrow(() -> new RuntimeException("No path found!")));
			redrawGrid();
		});
		controls.add(gridSize);
		controls.add(solveButton);

		JPanel grid = new JPanel();
		int size = 15;

		grid.setLayout(new GridLayout(size, size));
		initMaze(size, size);
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				Vector2 coordinate = Vector2.of(x, y);
				MazeButton mazeButton = matrix.get(coordinate).map(MazeButton::new).orElseThrow(() -> new RuntimeException("No MazePoint found at: " + coordinate));
				mazeButton.addActionListener(e -> {
					this.setSolvedPath(new ArrayList<>());
					MazePoint mp = matrix.get(coordinate).get();
					boolean isWall = mp.equals(MazePoint.WALL);
					matrix.set(coordinate, isWall ? MazePoint.EMPTY : MazePoint.WALL);
					redrawGrid();
				});
				matrixButtons.set(coordinate, mazeButton);
				grid.add(mazeButton);
			}
		}
		jPanel.add(grid);
		jPanel.add(controls);
	}

	public List<Vector2> getSolvedPath() {
		return solvedPath;
	}

	public void setSolvedPath(List<Vector2> solvedPath) {
		this.solvedPath = solvedPath;
	}

	private void redrawGrid() {
		this.matrixButtons.getElements().forEach((coordinate, mazeButton) -> {
			mazeButton.setMazePoint(matrix.get(coordinate).get());
			mazeButton.setOnPath(getSolvedPath().contains(coordinate));
			mazeButton.reDraw();
		});
	}

}
