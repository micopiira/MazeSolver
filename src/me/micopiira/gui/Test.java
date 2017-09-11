package me.micopiira.gui;

import me.micopiira.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test {
	private final MazeSolver mazeSolver;
	private Matrix<MazePoint> matrix = new Matrix<>(new HashMap<>());
	private List<Coordinate> solvedPath = new ArrayList<>();
	private Matrix<MazeButton> matrixButtons = new Matrix<>(new HashMap<>());

	public Test(MazeSolver mazeSolver) {
		this.mazeSolver = mazeSolver;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Test(new AStarMazeSolver()).createAndShowGUI());
	}

	private void initMaze(int cols, int rows) {
		for (int y = 0; y < cols; y++) {
			for (int x = 0; x < rows; x++) {
				matrix.set(Coordinate.of(x, y), MazePoint.EMPTY);
			}
		}
		matrix.set(Coordinate.of(0, 0), MazePoint.START);
		matrix.set(Coordinate.of(cols - 1, rows - 1), MazePoint.GOAL);
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

		solveButton.addActionListener(e -> {
			this.setSolvedPath(mazeSolver.solve(matrix).orElseThrow(() -> new RuntimeException("No path found!")));
			redrawGrid();
		});

		controls.add(solveButton);

		JPanel grid = new JPanel();
		int size = 10;

		grid.setLayout(new GridLayout(size, size));
		initMaze(size, size);
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				Coordinate coordinate = Coordinate.of(x, y);
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

	public List<Coordinate> getSolvedPath() {
		return solvedPath;
	}

	public void setSolvedPath(List<Coordinate> solvedPath) {
		this.solvedPath = solvedPath;
	}

	private void redrawGrid() {
		this.matrixButtons.getMazePoints().forEach((coordinate, mazeButton) -> {
			mazeButton.setMazePoint(matrix.get(coordinate).get());
			mazeButton.setOnPath(getSolvedPath().contains(coordinate));
			mazeButton.reDraw();
		});
	}

}
