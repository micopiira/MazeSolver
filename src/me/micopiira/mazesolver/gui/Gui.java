package me.micopiira.mazesolver.gui;

import me.micopiira.mazesolver.math.Matrix;
import me.micopiira.mazesolver.math.Vector2;
import me.micopiira.mazesolver.maze.MazePoint;
import me.micopiira.mazesolver.maze.MazeSolver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Gui {
	private static final String TITLE = "Gui";
	private static final int DEFAULT_SIZE = 15;
	private List<Vector2> solvedPath = new ArrayList<>();
	private Matrix<MazePoint> maze = createMaze(DEFAULT_SIZE);
	private final MazeSolver mazeSolver;

	public Gui(MazeSolver mazeSolver) {
		this.mazeSolver = mazeSolver;
	}

	private static Matrix<MazePoint> createMaze(int size) {
		Matrix<MazePoint> m = new Matrix<>(MazePoint.EMPTY, size);
		m.set(Vector2.of(0, 0), MazePoint.START);
		m.set(Vector2.of(size - 1, size - 1), MazePoint.GOAL);
		return m;
	}

	public void createAndShowGUI() {
		JPanel jPanel = new JPanel();
		JFrame frame = new JFrame(TITLE);
		frame.setContentPane(jPanel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setVisible(true);

		jPanel.setLayout(new GridLayout(2, 1));
		jPanel.setBackground(Color.BLACK);

		ControlsPanel controls = new ControlsPanel();
		GridPanel grid = new GridPanel(DEFAULT_SIZE, maze, solvedPath);

		controls.setGridSizeButton.addActionListener(e -> {
			solvedPath.clear();
			int gridSize = Integer.parseInt(controls.gridSize.getText());
			grid.setSize(gridSize);
			maze.setElements(createMaze(gridSize).getElements());
			grid.recreateGrid();
		});

		controls.solveButton.addActionListener(e -> {
			solvedPath.clear();
			solvedPath.addAll(mazeSolver.solve(maze).orElseThrow(() -> new RuntimeException("No path found!")));
			grid.redraw();
		});

		jPanel.add(grid);
		jPanel.add(controls);
	}
}
