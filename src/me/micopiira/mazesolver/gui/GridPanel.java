package me.micopiira.mazesolver.gui;

import me.micopiira.mazesolver.math.Matrix;
import me.micopiira.mazesolver.math.Vector2;
import me.micopiira.mazesolver.maze.MazePoint;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

class GridPanel extends JPanel {

	private final Matrix<MazeButton> buttons = new Matrix<>(new HashMap<>());

	GridPanel(int size) {
		setLayout(new GridLayout(size, size));
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				Vector2 coordinate = Vector2.of(x, y);
				MazeButton mazeButton = Main.maze.get(coordinate).map(MazeButton::new).orElseThrow(() -> new RuntimeException("No MazePoint found at: " + coordinate));
				mazeButton.addActionListener(e -> {
					Main.solvedPath = new ArrayList<>();
					MazePoint mp = Main.maze.get(coordinate).get();
					boolean isWall = mp.equals(MazePoint.WALL);
					Main.maze.set(coordinate, isWall ? MazePoint.EMPTY : MazePoint.WALL);
					redraw();
				});
				buttons.set(coordinate, mazeButton);
				add(mazeButton);
			}
		}

	}

	void redraw() {
		this.buttons.getElements().forEach((coordinate, mazeButton) -> {
			mazeButton.setMazePoint(Main.maze.get(coordinate).get());
			mazeButton.setOnPath(Main.solvedPath.contains(coordinate));
			mazeButton.reDraw();
		});
	}
}