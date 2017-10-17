package me.micopiira.mazesolver.gui;

import me.micopiira.mazesolver.math.Matrix;
import me.micopiira.mazesolver.math.Vector2;
import me.micopiira.mazesolver.maze.MazePoint;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

class GridPanel extends JPanel {

	private final Matrix<MazePoint> maze;
	private final List<Vector2> solvedPath;
	private final Matrix<MazeButton> buttons = new Matrix<>();
	private int size;

	GridPanel(int size, Matrix<MazePoint> maze, List<Vector2> solvedPath) {
		this.maze = maze;
		this.solvedPath = solvedPath;
		this.size = size;
	}

	void setSize(int size) {
		this.size = size;
	}

	void recreateGrid() {
		buttons.setElements(new HashMap<>());
		removeAll();
		revalidate();
		setLayout(new GridLayout(size, size));
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				Vector2 coordinate = Vector2.of(x, y);
				MazeButton mazeButton = maze.get(coordinate).map(MazeButton::new).orElseThrow(() -> new RuntimeException("No MazePoint found at: " + coordinate));
				mazeButton.setOnPath(false);
				mazeButton.addActionListener(e -> {
					solvedPath.clear();
					boolean isWall = maze.get(coordinate).filter(MazePoint.WALL::equals).isPresent();
					maze.set(coordinate, isWall ? MazePoint.EMPTY : MazePoint.WALL);
					mazeButton.setMazePoint(maze.get(coordinate).get());
					mazeButton.setOnPath(false);
					mazeButton.reDraw();
					redraw();
				});
				add(mazeButton);
				buttons.set(coordinate, mazeButton);
			}
		}
		repaint();
	}

	void redraw() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				Vector2 coordinate = Vector2.of(x, y);
				MazeButton mazeButton = buttons.get(coordinate).get();
				mazeButton.setOnPath(solvedPath.contains(coordinate));
				mazeButton.reDraw();
			}
		}
	}
}
