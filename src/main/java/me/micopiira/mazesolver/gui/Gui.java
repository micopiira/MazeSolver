package me.micopiira.mazesolver.gui;

import me.micopiira.mazesolver.math.Matrix;
import me.micopiira.mazesolver.math.Vector2;
import me.micopiira.mazesolver.maze.MazePoint;
import me.micopiira.mazesolver.maze.MazeSolver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Gui {
	private static final String TITLE = "Gui";
	private static final int DEFAULT_SIZE = 15;
	private final List<Vector2> solvedPath = new ArrayList<>();
	private final Matrix<MazePoint> maze = new Matrix<>();
	private final MazeSolver mazeSolver;
	private final Random random = new Random();

	public Gui(MazeSolver mazeSolver) {
		this.mazeSolver = mazeSolver;
	}

	private static Matrix<MazePoint> createMaze(int size, Vector2 start, Vector2 target) {
		Matrix<MazePoint> m = new Matrix<>(MazePoint.EMPTY, size);
		m.set(start, MazePoint.START);
		m.set(target, MazePoint.GOAL);
		return m;
	}

	public void createAndShowGUI() {
		JPanel jPanel = new JPanel();
		JFrame frame = new JFrame(TITLE);
		frame.setContentPane(jPanel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setVisible(true);

		jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
		jPanel.setBackground(Color.BLACK);

		ControlsPanel controls = new ControlsPanel();
		GridPanel grid = new GridPanel(DEFAULT_SIZE, maze, solvedPath);

		controls.randomizeBtn.addActionListener(e -> {
			solvedPath.clear();
			int gridSize = (int) controls.gridSize.getValue();
			grid.setSize(gridSize);
			Matrix<MazePoint> randomized = new Matrix<>(() -> random.nextInt(10) < 2 ? MazePoint.WALL : MazePoint.EMPTY, gridSize);
			randomized.set(controls.startCoords.getVector2(), MazePoint.START);
			randomized.set(controls.targetCoords.getVector2(), MazePoint.GOAL);
			maze.setElements(randomized.getElements());
			grid.recreateGrid();
		});

		controls.setGridSizeButton.addActionListener(e -> {
			solvedPath.clear();
			int gridSize = (int) controls.gridSize.getValue();
			grid.setSize(gridSize);
			maze.setElements(
					createMaze(gridSize, controls.startCoords.getVector2(), controls.targetCoords.getVector2()).getElements()
			);
			grid.recreateGrid();
		});

		controls.solveButton.addActionListener(e -> {
			solvedPath.clear();
			solvedPath.addAll(mazeSolver.solve(maze).orElseThrow(() -> new RuntimeException("No path found!")));
			grid.redraw();
		});

		grid.setPreferredSize(new Dimension(800, 800 - 150));
		controls.setPreferredSize(new Dimension(0, 150));

		jPanel.add(grid);
		jPanel.add(controls);

		controls.setGridSizeButton.doClick();
	}
}
