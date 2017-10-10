package me.micopiira.mazesolver.gui;

import me.micopiira.mazesolver.math.Matrix;
import me.micopiira.mazesolver.math.Vector2;
import me.micopiira.mazesolver.maze.AStarMazeSolver;
import me.micopiira.mazesolver.maze.MazePoint;
import me.micopiira.mazesolver.maze.MazeSolver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Main {
	private static final String TITLE = "Main";
	static List<Vector2> solvedPath = new ArrayList<>();
	private static int gridSize = 15;
	static Matrix<MazePoint> maze = initMaze(gridSize);
	private final MazeSolver mazeSolver;

	private Main(MazeSolver mazeSolver) {
		this.mazeSolver = mazeSolver;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Main(new AStarMazeSolver()).createAndShowGUI());
		Thread.setDefaultUncaughtExceptionHandler((t, e) -> JOptionPane.showMessageDialog(null, e.toString(), e.getMessage(), JOptionPane.ERROR_MESSAGE));
	}

	private static Matrix<MazePoint> initMaze(int size) {
		Matrix<MazePoint> m = new Matrix<>(MazePoint.EMPTY, size);
		m.set(Vector2.of(0, 0), MazePoint.START);
		m.set(Vector2.of(size - 1, size - 1), MazePoint.GOAL);
		return m;
	}

	private void createAndShowGUI() {
		JPanel jPanel = new JPanel();
		JFrame frame = new JFrame(TITLE);
		frame.setContentPane(jPanel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setVisible(true);

		jPanel.setLayout(new GridLayout(2, 1));
		jPanel.setBackground(Color.BLACK);

		ControlsPanel controls = new ControlsPanel();
		GridPanel grid = new GridPanel(gridSize);

		controls.solveButton.addActionListener(e -> {
			solvedPath = mazeSolver.solve(maze).orElseThrow(() -> new RuntimeException("No path found!"));
			grid.redraw();
		});

		jPanel.add(grid);
		jPanel.add(controls);
	}
}